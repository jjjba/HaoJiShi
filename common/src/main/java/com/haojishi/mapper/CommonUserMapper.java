package com.haojishi.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * @author 梁闯
 * @date 2018/1/11 0011
 */
public interface CommonUserMapper {

    Map<String, Object> queryPersonalInfo(@Param("userId") Long userId);
    List<Map<String,Object>> findPersonalByPars(@Param("name") String name, @Param("phone") String phone);
}
