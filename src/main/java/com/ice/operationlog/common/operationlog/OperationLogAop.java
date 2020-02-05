package com.ice.operationlog.common.operationlog;

import com.ice.operationlog.common.operationlog.utils.OperationLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class OperationLogAop {

    private static SpelExpressionParser spelParser = new SpelExpressionParser();


    @Around("@annotation(operationLog)")
    public void around(ProceedingJoinPoint point, OperationLog operationLog){
        Object[] args = point.getArgs();
        log.info("记录操作日志，获取注解{}, 请求参数：{}", operationLog, args);

        //创建上下文，然后将请求参数注入SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < args.length; i++) {
            context.setVariable("p" + i, args[i]);
        }

        //执行目标方法
        Object returnObj = null;
        try {
            OperationLogUtil.setStandardEvaluationContext(operationLog.value(), context);
            returnObj = point.proceed(args);
        } catch (Throwable throwable) {
            log.error("记录操作日志AOP，执行目标方法抛出异常", throwable);
        }
        log.info("记录操作日志，获取返回值：{}", returnObj);

        //将返回值注入上下文，然后解析表达式
        try {
            context.setVariable("ret", returnObj);
            Expression expression = spelParser.parseExpression(
                    "'用户' + #p0 + '在' + T(System).currentTimeMillis() + '登录, id是' + #p2?.id " +
                            "+ ', 性别是' + #ret?.sex + '，此外在运行时产生了数据：' + #runtime");
            Object value = expression.getValue(context);
            log.info("获取表达式结果：{}", value);
        }catch (Exception e){
            log.error("解析SPEL表达式失败", e);
        }
    }
}
