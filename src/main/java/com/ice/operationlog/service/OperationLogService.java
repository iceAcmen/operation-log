package com.ice.operationlog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ice.operationlog.mapper.entity.OperationLog;
import com.ice.operationlog.service.dto.OperationLogDto;

public interface OperationLogService {
    void save(OperationLog operationLog);

    IPage<OperationLog> page(OperationLogDto operationLogDto);
}
