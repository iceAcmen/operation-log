package com.ice.operationlog.common.operationlog.components;

import com.alibaba.fastjson.JSON;
import com.ice.operationlog.controller.OperationLogController;
import com.ice.operationlog.mapper.entity.OperationLog;
import com.ice.operationlog.service.dto.OperationLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Ice, You're very best
 * @date 2020/2/6 13:45
 * @desc 操作日志消息发布者
 */
public class OperationLogPublisher {
    @Autowired
    private OperationLogController operationLogController;

    /**
     * 先简单做，直接用feign调用持久层
     * 后续可支持使用消息发布机制解耦
     * @param operationLogDto
     */
    @Async
    public void publish(OperationLogDto operationLogDto){
        operationLogController.save(JSON.toJavaObject((JSON)JSON.toJSON(operationLogDto), OperationLog.class));
    }
}
