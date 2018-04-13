package com.haojishi.mapper;

import com.haojishi.model.Personal;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface PersonalMapper extends Mapper<Personal> {
    List<Map<String,Object>> phoneNumber(@Param("sql") String sql);
}