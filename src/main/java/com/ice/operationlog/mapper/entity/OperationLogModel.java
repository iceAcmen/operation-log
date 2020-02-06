package com.ice.operationlog.mapper.entity;

import lombok.Data;

@Data
public class OperationLogModel {

    /** id */
    private String id;

    /** modelKey */
    private String modelKey;

    /** module */
    private String module;

    /** target */
    private String target;

    /** content */
    private String content;

}
