package com.ice.operationlog.controller;

import com.ice.operationlog.mapper.entity.OperationLogModel;
import com.ice.operationlog.service.OperationLogModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ice, You're very best
 * @date 2020/2/6 12:46
 * @desc
 */
@RequestMapping("/operationlogmodel")
@RestController
public class OperationLogModelController {

    @Autowired
    private OperationLogModelService operationLogModelService;

    @GetMapping
    public List<OperationLogModel> listAll(){
        return operationLogModelService.listAll();
    }
}
