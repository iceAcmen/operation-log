package com.ice.operationlog.service;

import com.ice.operationlog.mapper.entity.OperationLogModel;

import java.util.List;

public interface OperationLogModelService {
    List<OperationLogModel> listAll();
}
