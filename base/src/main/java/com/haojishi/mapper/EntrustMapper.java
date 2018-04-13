package com.haojishi.mapper;

import com.haojishi.model.Entrust;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface EntrustMapper extends Mapper<Entrust> {
    List<Map<String,Object>> CompanyJy(@Param("sql") String sql);
}