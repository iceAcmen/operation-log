package com.ice.operationlog.service.dto;

import com.ice.operationlog.mapper.entity.OperationResult;
import com.ice.operationlog.mapper.entity.OperationSystem;
import com.ice.operationlog.mapper.entity.OperatorType;
import lombok.Data;

@Data
public class OperationLogDto {

    /** id */
    private Integer id;

    /** operator_account */
    private String operatorAccount;

    /** operator_type */
    private OperatorType operatorType;

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
    private Long operationTime;

    /** content */
    private String content;

    /** result */
    private OperationResult result;

    /** fail_reason */
    private String failReason;

    /** system */
    private OperationSystem system;

    public OperationLogDto() {
    }

    public OperationLogDto(OperationLogModelDto logModel) {
        this.module = logModel.getModule();
        this.target = logModel.getTarget();
    }
}
