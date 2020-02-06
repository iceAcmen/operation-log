package com.ice.operationlog.common.operationlog.components;

import com.ice.operationlog.common.operationlog.OperationLogAop;
import com.ice.operationlog.common.operationlog.utils.OperationLogHttpRequestResolver;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;


/**
 * @author Ice, You're very best
 * @date 2020/2/6 12:16
 * @desc 开启操作日志相关的类
 */
public class OperationLogImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                //AOP处理类
                OperationLogAop.class.getName(),
                //操作日志模板容器类
                OperationLogModelContainer.class.getName(),
                //操作日志发布者类
                OperationLogPublisher.class.getName(),
        };
    }
}
