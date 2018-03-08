package com.haojishi.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 王强
 * @date 2018/1/13 0013.
 */
public interface CommonResumeMapper {

    List<Map<String, Object>> getMyResumeByUserId(@Param("userId") Integer userId);

    List<Map<String, Object>> getMyResumeByCompanyId(@Param("companyId") Integer companyId);
}
