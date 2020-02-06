package com.ice.operationlog.common.operationlog.utils;

import com.alibaba.fastjson.JSONObject;
import com.ice.operationlog.common.operationlog.utils.HttpRequestUtil;
import com.ice.operationlog.mapper.entity.OperationSystem;
import com.ice.operationlog.mapper.entity.OperatorType;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ice, You're very best
 * @date 2020/2/6 14:49
 * @desc 操作日志http请求解析器
 */
public class OperationLogHttpRequestResolver {
    private HttpServletRequest httpRequest;
    private JSONObject userInfo;

    public static final String USER_CODE_HEADER_KEY = "mock_user";
    public static final String SYSTEM_HEADER_KEY = "system";
    public static final String OPERATION_TIME_HEADER_KEY = "operation_time";

    public OperationLogHttpRequestResolver() {
        this.httpRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public String getIp(){
        return HttpRequestUtil.getIp(this.httpRequest);
    }

    public OperatorType getOperatorType(){
        // TODO: 2020/2/6 根据实际情况写
        JSONObject userInfo = getUserInfo();
        int isAdmin = userInfo.getIntValue("isAdmin");
        return isAdmin == 0 ? OperatorType.ADMIN : OperatorType.USER;
    }

    public String getOperatorName(){
        return getUserInfo().getString("operatorName");
    }

    public String getRoleName(){
        return getUserInfo().getString("roleName");
    }

    public String getOperatorAccount() {
        return getUserInfo().getString("account");
    }

    public OperationSystem getOperationSystem(){
        String system = this.httpRequest.getHeader(SYSTEM_HEADER_KEY);
        if (StringUtils.isBlank(system)) {
            system = "OPERATION";
        }
        return OperationSystem.valueOf(system);
    }

    public Long getOperationTime(){
        String operationTime = this.httpRequest.getHeader(OPERATION_TIME_HEADER_KEY);
        if (StringUtils.isBlank(operationTime)) {
            return System.currentTimeMillis();
        }
        return Long.valueOf(operationTime);
    }

    public JSONObject getUserInfo(){
        if (null != this.userInfo) {
            return this.userInfo;
        }
        String userCode = this.httpRequest.getHeader(USER_CODE_HEADER_KEY);
        // TODO: 2020/2/6 根据userCode从Redis中取出用户信息
        return this.userInfo = new JSONObject()
                .fluentPut("isAdmin", "0")
                .fluentPut("account", "ice001")
                .fluentPut("roleName", "超级管理员")
                .fluentPut("operatorName", "史冰冰")
                ;
    }
}
