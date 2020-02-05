package com.ice.operationlog.controller;

import com.alibaba.fastjson.JSONObject;
import com.ice.operationlog.common.operationlog.OperationLog;
import com.ice.operationlog.common.operationlog.utils.OperationLogUtil;
import com.ice.operationlog.service.dto.UserDto;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ice, You're very best
 * @date 2020/2/5 9:35
 * @desc 用户Rest接口
 */
@RestController
public class UserController {

    @OperationLog(value = "login")
    @PostMapping("/login/{userName}")
    public UserDto login(@PathVariable("userName") String userName, @RequestParam("password") String password,
                         @RequestBody UserDto userDto) {
        OperationLogUtil.setLogData("login", new JSONObject().fluentPut("runtime", "qqq"));
        return new UserDto(1, "ice", 28, System.currentTimeMillis(), "广东佛山", "BOY", "NORMAL");
    }
}
