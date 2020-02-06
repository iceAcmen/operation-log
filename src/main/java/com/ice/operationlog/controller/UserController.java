package com.ice.operationlog.controller;

import com.alibaba.fastjson.JSONObject;
import com.ice.operationlog.common.operationlog.annos.OperationLog;
import com.ice.operationlog.common.operationlog.utils.OperationLogContextUtil;
import com.ice.operationlog.service.dto.UserDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ice, You're very best
 * @date 2020/2/5 9:35
 * @desc 用户Rest接口
 */
@RestController
public class UserController {

    @OperationLog("login")
    @PostMapping("/login/{userName}")
    public UserDto login(@PathVariable("userName") String userName, @RequestParam("password") String password,
                         @RequestBody UserDto userDto) {
        OperationLogContextUtil.setLogData("login", new JSONObject().fluentPut("runtime", "qqq"));
        if (StringUtils.equals(password, "123")) {
            throw new RuntimeException("运行时异常");
        }
        return new UserDto(1, "ice", 28, System.currentTimeMillis(), "广东佛山", "BOY", "NORMAL");
    }
}
