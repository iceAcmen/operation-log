package com.ice.operationlog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.operationlog.mapper.OperationLogMapper;
import com.ice.operationlog.mapper.entity.OperationLog;
import com.ice.operationlog.service.OperationLogService;
import com.ice.operationlog.service.dto.OperationLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public IPage<OperationLog> page(OperationLogDto operationLogDto) {
        return operationLogMapper.selectPage(new Page<>(), new QueryWrapper<>());
    }
}
