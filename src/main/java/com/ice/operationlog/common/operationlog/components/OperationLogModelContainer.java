package com.ice.operationlog.common.operationlog.components;

import com.alibaba.fastjson.JSON;
import com.ice.operationlog.controller.OperationLogModelController;
import com.ice.operationlog.mapper.entity.OperationLogModel;
import com.ice.operationlog.service.dto.OperationLogModelDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ice, You're very best
 * @date 2020/2/6 12:57
 * @desc 操作日志模板容器
 */
@Slf4j
public class OperationLogModelContainer {
    private static final Map<String, OperationLogModelDto> OPERATION_LOG_MODEL_MAP = new ConcurrentHashMap<>();

    @Autowired
    private OperationLogModelController operationLogModelController;

    @PostConstruct
    public void init(){
        List<OperationLogModel> operationLogModelEntities = operationLogModelController.listAll();
        operationLogModelEntities.forEach(entity->{
            OperationLogModelDto logModelDto = JSON.toJavaObject((JSON) JSON.toJSON(entity), OperationLogModelDto.class);
            OPERATION_LOG_MODEL_MAP.put(entity.getModelKey(), logModelDto);
        });
    }

    public static OperationLogModelDto get(String key){
        return OPERATION_LOG_MODEL_MAP.get(key);
    }
}
