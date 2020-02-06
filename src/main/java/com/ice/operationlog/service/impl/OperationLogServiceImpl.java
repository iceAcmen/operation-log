package com.ice.operationlog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.operationlog.mapper.OperationLogMapper;
import com.ice.operationlog.mapper.entity.OperationLog;
import com.ice.operationlog.service.OperationLogService;
import com.ice.operationlog.service.dto.OperationLogDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * @author Ice, You're very best
 * @date 2020/2/6 10:09
 * @desc
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public void save(OperationLog operationLog) {
        operationLogMapper.insert(operationLog);
    }

    @Override
    public IPage<OperationLog> page(Integer pageNo, Integer pageSize, Long startTime, Long endTime, OperationLog queryParam) {
        String operatorAccount = queryParam.getOperatorAccount();
        String module = queryParam.getModule();
        String content = queryParam.getContent();
        String target = queryParam.getTarget();

        QueryWrapper<OperationLog> queryWrapper = new QueryWrapper<>();
        if (isNotBlank(operatorAccount)) {
            queryWrapper.like("operator_account", operatorAccount);
        }
        if (isNotBlank(module)) {
            queryWrapper.like("module", module);
        }
        if (isNotBlank(content)) {
            queryWrapper.like("content", content);
        }
        if (isNotBlank(target)) {
            queryWrapper.like("target", target);
        }
        if (null != startTime) {
            queryWrapper.ge("operation_time", startTime);
        }
        if (null != endTime) {
            queryWrapper.le("operation_time", endTime);
        }
        return operationLogMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);
    }
}
