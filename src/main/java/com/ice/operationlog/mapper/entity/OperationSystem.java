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
public enum OperationSystem {
    PORTAL("门户"),
    OPERATION("运营中心")
    ;
    /** desc */
    private String desc;
}
