package com.haojishi.controller;

import com.haojishi.service.FinanceService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 梁闯
 * @date 2018/03/14 20.47
 */
@RestController
@RequestMapping("finance")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    /**
     * 获取所有快招数据
     *
     * @param page
     * @param size
     * @return BusinessMessage - 所有快招数据
     */
    @RequestMapping("getAllKuaiZhao")
    public BusinessMessage getAllKuaiZhao(Integer page, Integer size){
        return financeService.getAllKuaiZhao(page,size);
    }
}
