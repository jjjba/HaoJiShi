package com.haojishi.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 梁闯
 * @date 2018/03/14 22.42
 */
public interface CommonFinanceMapper {

    List<Map<String, Object>> getAllKuaiZhao();
}
