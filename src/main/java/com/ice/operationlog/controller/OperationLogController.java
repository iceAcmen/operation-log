package com.ice.operationlog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ice.operationlog.mapper.entity.OperationLog;
import com.ice.operationlog.service.OperationLogService;
import com.ice.operationlog.service.dto.OperationLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/page")
    public IPage<OperationLog> page(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,
                                    @RequestParam(value = "startTime", required = false) Long startTime,
                                    @RequestParam(value = "endTime", required = false) Long endTime,
                                    @RequestBody OperationLog queryParam){
        return operationLogService.page(pageNo, pageSize, startTime, endTime, queryParam);
    }
}
