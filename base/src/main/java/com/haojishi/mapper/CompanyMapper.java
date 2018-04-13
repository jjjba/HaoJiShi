package com.haojishi.mapper;

import com.haojishi.model.Company;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface CompanyMapper extends Mapper<Company> {
    List<Map<String, Object>> executeSql(@Param("sql") String sql);
    List<Map<String, Object>> selectCompany(@Param("sql") String sql);
}