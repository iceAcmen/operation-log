package com.ice.operationlog.service.dto;

import lombok.Data;

@Data
public class OperationLogModelDto {

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
