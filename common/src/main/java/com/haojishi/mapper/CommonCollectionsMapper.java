package com.haojishi.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 王强
 * @date 2018/1/13 0013.
 */
public interface CommonCollectionsMapper {

    List<Map<String, Object>> getMyCollectionsByUserId(@Param("userId") Integer userId);
}
