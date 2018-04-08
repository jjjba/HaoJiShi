package com.haojishi.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 梁闯
 * @date 2018/03/07 20.42
 *
 */
public interface CommonPersonalMapper {

    List<Map<String, Object>> findPersonalByAddress(@Param("address") String address);
    //根据姓名手机号查询
    List<Map<String,Object>> findPersonalByPars(@Param("name") String name, @Param("phone") String phone);
    //查询求职者数据
    List<Map<String,Object>> getPersonalByCity(@Param("city") String city);
}
