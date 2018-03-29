package com.haojishi.service;


import com.haojishi.mapper.IndexModuleMapper;
import com.haojishi.model.IndexModule;
import com.haojishi.util.BusinessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 梁闯
 * @date 2018/03/27 15.34
 */
@Slf4j
@Service
public class IndexModuleService {

    @Autowired
    private IndexModuleMapper indexModuleMapper;

    /**
     * 获取企业端首页四个活接口数据
     *
     * @return
     */
    public BusinessMessage getIndexModule(){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            Example moduleexample =new Example(IndexModule.class);
            moduleexample.setOrderByClause("sort");
            List<IndexModule> indexModuleList =indexModuleMapper.selectByExample(moduleexample);
            businessMessage.setData(indexModuleList);
            businessMessage.setSuccess(true);
            businessMessage.setMsg("获取企业端四个接口成功");
        }catch (Exception e){
            log.error("获取企业活接口失败");
        }
        return businessMessage;
    }
}
