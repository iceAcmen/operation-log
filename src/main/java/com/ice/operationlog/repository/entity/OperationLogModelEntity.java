package com.ice.operationlog.repository.entity;

import lombok.Data;

@Data
public class OperationLogModelEntity {

    /** id */
    private String id;

    /** key */
    private String key;

    /** module */
    private String module;

    /** target */
    private String target;

    /** content */
    private String content;

}
