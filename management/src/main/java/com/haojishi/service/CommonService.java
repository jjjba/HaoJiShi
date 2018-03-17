package com.haojishi.service;

import com.haojishi.mapper.RegionMapper;
import com.haojishi.model.Region;
import com.haojishi.util.BusinessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 梁闯
 * @date 2018/03/17 13.21
 */
@Slf4j
@Service
public class CommonService {
    @Autowired
    private RegionMapper regionMapper;

    /**
     * 读取城市列表根据PID
     * @param pid
     * @return
     */
    public BusinessMessage findRegionListByPid(Integer pid) {
        BusinessMessage businessMessage = new BusinessMessage(false);
        try{
            Example example = new Example(Region.class);
            example.createCriteria().andEqualTo("pid", pid);
            List<Region> joinsList = this.regionMapper.selectByExample(example);
            if (joinsList != null && joinsList.size() > 0) {
                businessMessage.setData(joinsList);
                businessMessage.setSuccess(true);
            } else {
                businessMessage.setMsg("暂无数据");
                businessMessage.setSuccess(true);
            }
        }catch(Exception e){
            log.error("获取分页查询信息失败", e);
            businessMessage.setMsg("获取城市列表不存在，请重试");
        }
        return businessMessage;
    }
}
