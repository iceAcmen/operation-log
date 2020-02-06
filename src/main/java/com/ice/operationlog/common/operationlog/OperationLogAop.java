package com.ice.operationlog.common.operationlog;

import com.ice.operationlog.common.operationlog.annos.OperationLog;
import com.ice.operationlog.common.operationlog.components.OperationLogModelContainer;
import com.ice.operationlog.common.operationlog.components.OperationLogPublisher;
import com.ice.operationlog.common.operationlog.utils.OperationLogHttpRequestResolver;
import com.ice.operationlog.common.operationlog.utils.OperationLogBuilder;
import com.ice.operationlog.common.operationlog.utils.OperationLogContextUtil;
import com.ice.operationlog.mapper.entity.OperationResult;
import com.ice.operationlog.service.dto.OperationLogDto;
import com.ice.operationlog.service.dto.OperationLogModelDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@Aspect
@Slf4j
public class OperationLogAop {

    private static SpelExpressionParser spelParser = new SpelExpressionParser();

    @Autowired
    private OperationLogPublisher operationLogPublisher;

    @Around("@annotation(operationLog)")
    public void around(ProceedingJoinPoint point, OperationLog operationLog) throws Throwable {
        OperationLogBuilder logBuilder;
        try {
            Object[] args = point.getArgs();
            log.info("获取目标方法注解{}, 请求参数：{}", operationLog, args);
            String modelKey = operationLog.value();

            //获取操作日志模板
            OperationLogModelDto logModel = OperationLogModelContainer.get(modelKey);
            if (null == logModel) {
                log.warn("获取操作日志模板失败，不记录操作日志，modelKey：{}", modelKey);
                //直接执行目标方法，不捕获异常，直接抛出
                point.proceed();
                return;
            }
            log.info("获取操作日志模板成功，modelKey：{}，logModel：{}", modelKey, logModel);

            //实例化操作日志建造者
            logBuilder = OperationLogBuilder.newOperationLog(logModel, new OperationLogHttpRequestResolver());

            //创建上下文，然后将请求参数注入SPEL上下文
            StandardEvaluationContext context = new StandardEvaluationContext();
            for (int i = 0; i < args.length; i++) {
                context.setVariable("p" + i, args[i]);
            }

            //执行目标方法
            Object returnObj = null;
            try {
                OperationLogContextUtil.setStandardEvaluationContext(modelKey, context);
                returnObj = point.proceed();
            } catch (Throwable throwable) {
                log.error("执行目标方法抛出异常", throwable);
                //将操作结果改为失败
                logBuilder.setResult(OperationResult.FAILED).setFailReason(throwable.toString());
            }
            log.info("获取返回值：{}", returnObj);

            //将返回值注入上下文，然后解析表达式
            context.setVariable("ret", returnObj);
            Expression expression = spelParser.parseExpression(
                    logModel.getContent());
            String value = expression.getValue(context, String.class);
            log.info("获取操作日志SPEL表达式解析结果：{}", value);

            //记录操作日志
            OperationLogDto operationLogDto = logBuilder.setContent(value).build();
            log.info("生成操作日志：{}", operationLogDto);
            operationLogPublisher.publish(operationLogDto);
        }catch (ParseException e){
            log.error("解析SPEL表达式失败", e);
        }catch (Exception e){
            log.error("记录操作日志失败", e);
        }
    }
}
