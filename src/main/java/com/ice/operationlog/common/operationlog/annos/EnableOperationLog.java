package com.ice.operationlog.common.operationlog.annos;

import com.ice.operationlog.common.operationlog.components.OperationLogImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(OperationLogImportSelector.class)
public @interface EnableOperationLog {
}
