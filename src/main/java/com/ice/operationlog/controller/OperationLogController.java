package com.ice.operationlog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ice.operationlog.mapper.entity.OperationLog;
import com.ice.operationlog.service.OperationLogService;
import com.ice.operationlog.service.dto.OperationLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ice, You're very best
 * @date 2020/2/6 12:46
 * @desc
 */
@RequestMapping("/operationlog")
@RestController
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    @PostMapping
    public void save(OperationLog operationLog){
        operationLogService.save(operationLog);
    }

    @GetMapping("/page")
    public IPage<OperationLog> page(OperationLogDto operationLogDto){
        return operationLogService.page(operationLogDto);
    }
}
