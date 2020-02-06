package com.ice.operationlog.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class OperationLog {

    /** id */
    @TableId(value = "id", type = IdType.AUTO)
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

}
