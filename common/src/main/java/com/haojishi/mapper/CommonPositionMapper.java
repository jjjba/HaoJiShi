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

    List<Map<String,Object>> getPositionByAddress(@Param("address") String address);

    List<Map<String,Object>> getPositionByAddressPro(@Param("pid") String pid);

    List<Map<String,Object>> getPositionByParams(@Param("city") String city,@Param("type") String type,@Param("money") String money,@Param("scale") String scale);

    List<Map<String,Object>> getPositionById(@Param("id") Integer id);

    List<Map<String,Object>> getAllPosition(@Param("name") String name, @Param("phone") String phone);

    List<Map<String,Object>> getAllPositionNotonline(@Param("name") String name, @Param("phone") String phone);

    List<Map<String,Object>> getPositionByParams02(@Param("city") String city,@Param("positionName") String positionName,@Param("money") String money,@Param("scale") String scale);

}
