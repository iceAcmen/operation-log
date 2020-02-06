package com.ice.operationlog.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Ice, You're very best
 * @date 2020/2/6 14:11
 * @desc
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OperationResult {
    SUCCESS("成功"),
    FAILED("失败")
    ;
    /** desc */
    private String desc;
}
