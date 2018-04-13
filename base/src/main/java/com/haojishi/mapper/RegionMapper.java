package com.haojishi.mapper;

import com.haojishi.model.Region;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface RegionMapper extends Mapper<Region> {
    List<Map<String, Object>> executeSql(@Param("sql") String sql);
}