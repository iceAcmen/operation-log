package com.ice.operationlog.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    /** id */
    private int id;
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
