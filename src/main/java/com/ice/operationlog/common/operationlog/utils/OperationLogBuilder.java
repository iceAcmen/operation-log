package com.ice.operationlog.common.operationlog.utils;

import com.ice.operationlog.mapper.entity.OperationResult;
import com.ice.operationlog.mapper.entity.OperationSystem;
import com.ice.operationlog.mapper.entity.OperatorType;
import com.ice.operationlog.service.dto.OperationLogDto;
import com.ice.operationlog.service.dto.OperationLogModelDto;

/**
 * @author Ice, You're very best
 * @date 2020/2/6 13:56
 * @desc 操作日志建造者
 */
public class OperationLogBuilder {
    private OperationLogDto operationLogDto;

    private OperationLogBuilder() {
        this.operationLogDto = new OperationLogDto();
        //默认操作成功，如果执行目标方法抛出异常则捕获异常将其设置为失败
        this.operationLogDto.setResult(OperationResult.SUCCESS);
    }

    private OperationLogBuilder(OperationLogModelDto logModelDto, OperationLogHttpRequestResolver requestResolver) {
        this.operationLogDto = new OperationLogDto(logModelDto);
        this.operationLogDto.setOperatorAccount(requestResolver.getOperatorAccount());
        this.operationLogDto.setOperatorName(requestResolver.getOperatorName());
        this.operationLogDto.setOperatorType(requestResolver.getOperatorType());
        this.operationLogDto.setRoleName(requestResolver.getRoleName());
        this.operationLogDto.setSystem(requestResolver.getOperationSystem());
        this.operationLogDto.setOperatorIp(requestResolver.getIp());
        this.operationLogDto.setOperationTime(requestResolver.getOperationTime());
        this.operationLogDto.setResult(OperationResult.SUCCESS);
    }

    public static OperationLogBuilder newOperationLog(){
        return new OperationLogBuilder();
    }

    public static OperationLogBuilder newOperationLog(OperationLogModelDto logModel, OperationLogHttpRequestResolver requestResolver){
        OperationLogBuilder builder = new OperationLogBuilder(logModel, requestResolver);
        return builder;
    }

    public OperationLogDto build(){
        return this.operationLogDto;
    }

    public OperationLogBuilder setId(Integer id) {
        this.operationLogDto.setId(id);
        return this;
    }

    public OperationLogBuilder setOperatorAccount(String operatorAccount) {
        this.operationLogDto.setOperatorAccount(operatorAccount);
        return this;
    }

    public OperationLogBuilder setOperatorType(OperatorType operatorType) {
        this.operationLogDto.setOperatorType(operatorType);
        return this;
    }

    public OperationLogBuilder setOperatorName(String operatorName) {
        this.operationLogDto.setOperatorName(operatorName);
        return this;
    }

    public OperationLogBuilder setRoleName(String roleName) {
        this.operationLogDto.setRoleName(roleName);
        return this;
    }

    public OperationLogBuilder setOperatorIp(String operatorIp) {
        this.operationLogDto.setOperatorIp(operatorIp);
        return this;
    }

    public OperationLogBuilder setModule(String module) {
        this.operationLogDto.setModule(module);
        return this;
    }

    public OperationLogBuilder setTarget(String target) {
        this.operationLogDto.setTarget(target);
        return this;
    }

    public OperationLogBuilder setOperationTime(Long operationTime) {
        this.operationLogDto.setOperationTime(operationTime);
        return this;
    }

    public OperationLogBuilder setContent(String content) {
        this.operationLogDto.setContent(content);
        return this;
    }

    public OperationLogBuilder setResult(OperationResult result) {
        this.operationLogDto.setResult(result);
        return this;
    }

    public OperationLogBuilder setFailReason(String failReason) {
        this.operationLogDto.setFailReason(failReason);
        return this;
    }

    public OperationLogBuilder setSystem(OperationSystem system) {
        this.operationLogDto.setSystem(system);
        return this;
    }
}
