package com.ice.operationlog.mapper.entity;

import lombok.Data;

@Data
public class UserEntity {
    /** username */
    private String username;
    /** age */
    private int age;
    /** birthday */
    private Long birthday;
    /** user_address */
    private String userAddress;
    /** sex */
    private String sex;
    /** status */
    private String status;
}
