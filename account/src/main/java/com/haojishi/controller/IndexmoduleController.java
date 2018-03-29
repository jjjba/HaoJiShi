package com.haojishi.controller;

import com.haojishi.service.IndexModuleService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("indexModule")
public class IndexmoduleController {

    @Autowired
    private IndexModuleService indexModuleService;

    /**
     * 获取企业端首页四个活接口数据
     *
     * @return
     */
    @RequestMapping("getIndexModule")
    public BusinessMessage getIndexModule(){
        return indexModuleService.getIndexModule();
    }
}
