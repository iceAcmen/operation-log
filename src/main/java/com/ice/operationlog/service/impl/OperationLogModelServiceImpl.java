package com.ice.operationlog.service.impl;

import com.ice.operationlog.mapper.OperationLogModelMapper;
import com.ice.operationlog.mapper.entity.OperationLogModel;
import com.ice.operationlog.service.OperationLogModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ice, You're very best
 * @date 2020/2/6 10:10
 * @desc
 */
@Service
public class OperationLogModelServiceImpl implements OperationLogModelService {
    @Autowired
    private OperationLogModelMapper operationLogModelMapper;

    @Override
    public List<OperationLogModel> listAll(){
        return operationLogModelMapper.selectList(null);
    }

}
