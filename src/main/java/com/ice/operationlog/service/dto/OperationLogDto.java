package com.ice.operationlog.service.dto;

import lombok.Data;

@Data
public class OperationLogDto {

    /** id */
    private String id;

    /** operator_account */
    private String operatorAccount;

    /** operator_type */
    private String operatorType;

    /** operator_name */
    private String operatorName;

    /** role_name */
    private String roleName;

    /** operator_ip */
    private String operatorIp;

    /** module */
    private String module;

    /** target */
    private String target;

    /** operation_time */
    private String operationTime;

    /** content */
    private String content;

    /** result */
    private String result;

    /** fail_reason */
    private String failReason;

    /** system */
    private String system;

}
