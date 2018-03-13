package com.haojishi.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 王强
 * @date 2018/1/11 0011
 */
public interface CommonCompanyMapper {


    int setSeeCount(@Param("companyId") Integer companyId);

    List<Map<String,Object>> findCompanyByPars(@Param("name") String name, @Param("phone") String phone);

    List<Map<String,Object>> positionNumSort(@Param("name") String name, @Param("phone") String phone);

    List<Map<String,Object>> seeCountNumSort(@Param("name") String name, @Param("phone") String phone);

    List<Map<String,Object>> payTimeSort(@Param("name") String name, @Param("phone") String phone);

    List<Map<String,Object>> getAllCompanyState(@Param("name") String name, @Param("phone") String phone);

    Map<String, Object> queryCompanyInfo(@Param("userId") Long userId);
}
