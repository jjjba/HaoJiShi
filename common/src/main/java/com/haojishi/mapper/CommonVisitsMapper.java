package com.haojishi.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 梁闯
 * @date 2018/03/16 20.43
 */
public interface CommonVisitsMapper {
    //平台访问次数
    List<Map<String, Object>> getNumberOfPlatformVisits(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
    //平台访问人数
    List<Map<String, Object>> getPlatformAccess(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
    //游客访问次数
    List<Map<String, Object>> getNumberOfVisitors(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
    //游客访问人数
    List<Map<String, Object>> getNumberOfVisitorsPer(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
    //企业访问次数
    List<Map<String, Object>> getNumberOfBusinessVisits(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
    //企业访问人数
    List<Map<String, Object>> getNumberOfIndividualVisitors(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
    //个人访问次数
    List<Map<String, Object>> getNumberOfPersonalVisits(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
    //个人访问人数
    List<Map<String, Object>> getNumberOfVisitor(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
    //分享次数
    List<Map<String, Object>> getNumberOfShare(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
    //分享人数
    List<Map<String, Object>> getTheNumberOfShare(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
}
