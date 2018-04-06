package com.haojishi.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 地区
 * @author 王强
 */
public interface CommonRegionMapper {

    /**
     * 通过大区获得所有的市
     * @param provinces 省的名字
     * @return
     */
    List<String> getCityNameByProvinceName(@Param("provinces") String[] provinces);

    String getProvinceByCity(@Param("comCity") String comCity);

    List<String> getCityBypId(@Param("pId") Integer pId);

}
