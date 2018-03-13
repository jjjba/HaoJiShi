package com.haojishi.controller;

import com.haojishi.service.CompanyService;
import com.haojishi.util.BusinessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 梁闯
 * @date 2018/03/13 15.56
 */
@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

//    @Autowired
//    private CommonService commonService;
    /**
     * 获取所有企业信息
     *
     * @param name
     * @param phone
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/getAllCompany")
    public BusinessMessage getAllCompany(String name, String phone, Integer page, Integer size){
        return companyService.getAllCompany(name,phone,page,size);
    }

    /**
     * 企业列表
     * @param name
     * @param phone
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("positionNumSort")
    public BusinessMessage positionNumSort(String name, String phone, Integer page, Integer size){
        return companyService.positionNumSort(name,phone,page,size);
    }

    /**
     * 企业列表
     * @param name
     * @param phone
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("seeCountNumSort")
    public BusinessMessage seeCountNumSort(String name, String phone, Integer page, Integer size){
        return companyService.seeCountNumSort(name,phone,page,size);
    }

    /**
     * 企业列表
     * @param name
     * @param phone
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("payTimeSort")
    public BusinessMessage payTimeSort(String name, String phone, Integer page, Integer size){
        return companyService.payTimeSort(name,phone,page,size);
    }


    /**
     * 根据企业ID获取企业信息
     * @param id
     * @return
     */
    @GetMapping("findCompanyInfoById")
    public BusinessMessage findById(Integer id) {
        return companyService.findOneByid(id);
    }

    /**
     * 跟新企业信息
     * @param request
     * @param id
     * @param company_name
     * @param company_scale
     * @param company_special_str
     * @param province
     * @param city
     * @param area
     * @param pid
     * @param company_info
     * @param company_photo
     * @param company_addrx
     * @param company_addry
     * @param company_addr
     * @param company_type
     * @param company_city
     * @return
     */
    @PostMapping("updateCompanyById")
    public BusinessMessage update(HttpServletRequest request, Integer id, String company_name, String company_scale, String company_special_str, Integer province, Integer city, Integer area, Integer pid, String company_info, MultipartFile company_photo,MultipartFile icon, String company_addrx, String company_addry, String company_addr, String company_type, String company_city) {
        return companyService.update(id,company_name,company_scale,company_special_str,province,city,area,pid,company_info,company_photo,icon,company_addrx,company_addry,company_addr, company_type,company_city);
    }
    /**
     * 读取城市列表根据pid
//     * @param pid
     * @return
     */
    @RequestMapping("regionList")
//    public BusinessMessage findRegionListByPid(Integer pid){
//        return commonService.findRegionListByPid(pid);
//    }

    /**
     * 编辑图片
     * @param request
     * @param id
     * @param image_url
     * @param photo
     * @return
     */
    @PostMapping("updatePhoto")
    public BusinessMessage updatePhoto(HttpServletRequest request, Integer id, MultipartFile image_url, String photo) {
        return companyService.updatePhoto(id,image_url,photo);
    }

    /**
     * 添加照片
     * @param request
     * @param id
     * @param image_url
     * @return
     */
    @PostMapping("insertPhoto")
    public BusinessMessage insertPhoto(HttpServletRequest request,Integer id, MultipartFile image_url) {
        return companyService.insertPhoto(id,image_url);
    }

    /**
     * 获取企业营业执照审核结果
     *
     * @param name
     * @param phone
     * @param page
     * @param size
     * @return BusinessMessage - 企业营业执照审核结果
     */
    @RequestMapping("/getAllCompanyState")
    public BusinessMessage getAllCompanyState(String name,String phone,Integer page,Integer size){
        return companyService.getAllCompanyState(name,phone,page,size);
    }
}
