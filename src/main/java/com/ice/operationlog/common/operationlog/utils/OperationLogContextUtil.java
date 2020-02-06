package com.ice.operationlog.common.operationlog.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author Ice, You're very best
 * @date 2020/2/5 17:23
 * @desc 操作日志上下文工具
 */
@Slf4j
public class OperationLogContextUtil {

    /**
     * 在操作日志的目标方法体中使用
     * 用于将目标方法执行过程中产生的数据注入OperationLogAop方法使用的context对象中
     * @param operationLogKey operation_log_model表中的key字段
     * @param data 要注入context的数据
     */
    public static void setLogData(String operationLogKey, JSONObject data){
        try {
            getStandardEvaluationContext(operationLogKey).setVariables(data);
        }catch (Exception e){
            log.error("运行时将数据注入spel context失败, key:{}，data:{}", operationLogKey, data, e);
        }
    }

    /**
     * 获取StandardEvaluationContext
     * @param operationLogKey
     * @return
     */
    private static StandardEvaluationContext getStandardEvaluationContext(String operationLogKey){
        return (StandardEvaluationContext) RequestContextHolder.currentRequestAttributes().getAttribute(operationLogKey, 1);
    }

    /**
     * 将StandardEvaluationContext放入RequestContextHolder
     * @param operationLogKey
     * @param context
     */
    public static void setStandardEvaluationContext(String operationLogKey, StandardEvaluationContext context){
        try {
            RequestContextHolder.currentRequestAttributes().setAttribute(operationLogKey, context, 1);
        }catch (Exception e){
            log.error("将spel context放进RequestContextHolder失败，key:{}", operationLogKey, e);
        }
    }
}
