package com.haojishi.mapper;

import com.haojishi.model.CollectPersonal;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface CollectPersonalMapper extends Mapper<CollectPersonal> {
    int deleCompany(@Param("sql") String sql);
    List<Map<String,Object>> PuanDuanQx(@Param("sql") String sql);

}