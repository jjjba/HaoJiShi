package com.haojishi.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 梁闯
 * @date 2018/3/21 23.48
 */
public interface CommonCollectionsMapper {

    List<Map<String, Object>> getCollectPositionByUserId(@Param("userId") Integer userId);
}
