package com.ice.operationlog.service.dto;

import lombok.Data;

@Data
public class OperationLogModelDto {

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
