package com.haojishi.service;

import com.haojishi.mapper.RegionMapper;
import com.haojishi.model.Region;
import com.haojishi.util.BusinessMessage;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Slf4j
@Service
public class RegionService {

    @Autowired
    private RegionMapper regionMapper;

    /**
     * 查询所有城市列表
     *
     * @return
     */
    public BusinessMessage getRegion(){
        BusinessMessage businessMessage =new BusinessMessage();
        Example regionExample =new Example(Region.class);
        regionExample.createCriteria().andLike("name","%市");
        List<Region> regionList =regionMapper.selectByExample(regionExample);
        businessMessage.setData(regionList);
        businessMessage.setMsg("获取城市列表成功");
        businessMessage.setSuccess(true);
        return businessMessage;
    }

    /**
     * 获取所有省份信息
     * @return
     */
    public BusinessMessage getProvince(){
        BusinessMessage businessMessage =new BusinessMessage();
        Example example =new Example(Region.class);
        example.createCriteria().andEqualTo("pid",1);
        List<Region> regions =regionMapper.selectByExample(example);
        businessMessage.setData(regions);
        businessMessage.setSuccess(true);
        businessMessage.setMsg("获取省份信息成功");
        return businessMessage;
    }

    /**
     * 获取所有省份信息
     * @return
     */
    public BusinessMessage getCityBypId(Integer id){
        BusinessMessage businessMessage =new BusinessMessage();
        Example example =new Example(Region.class);
        example.createCriteria().andEqualTo("pid",id);
        List<Region> regions =regionMapper.selectByExample(example);
        businessMessage.setData(regions);
        businessMessage.setMsg("根据省份获取城市数据成功");
        businessMessage.setSuccess(true);
        return businessMessage;
    }
}
