package com.haojishi.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 梁闯
 * @date 2018/03/15 20.42
 *
 */
public interface CommonPositionMapper {

    List<Map<String,Object>> getAllPosition(@Param("name") String name, @Param("phone") String phone);
    List<Map<String,Object>> getAllPositionNotonline(@Param("name") String name, @Param("phone") String phone);
}
