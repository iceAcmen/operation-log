package com.ice.operationlog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ice.operationlog.mapper.entity.OperationLogModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationLogModelMapper extends BaseMapper<OperationLogModel> {
}
