package com.haojishi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haojishi.mapper.CommonCompanyMapper;
import com.haojishi.mapper.CompanyMapper;
import com.haojishi.mapper.UserMapper;
import com.haojishi.model.Company;
import com.haojishi.model.Personal;
import com.haojishi.model.User;
import com.haojishi.util.BusinessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xzcy-01 on 2017/11/27.
 */
@Slf4j
@Service
public class CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private UserMapper usersMapper;

    @Autowired
    private CommonCompanyMapper commonCompanyMapper;

    @Autowired
    private Environment environment;

    /**
     * 获取所有企业信息
     *
     * @param name
     * @param phone
     * @param page
     * @param size
     * @return BusinessMessage - 所有企业信息
     */
    public BusinessMessage getAllCompany(String name, String phone, Integer page, Integer size) {
        BusinessMessage businessMessage = new BusinessMessage(false);
        try{
            if(null==page|| page <1){
                page=1;
            }
            if(null==size || size <1){
                size=10;
            }

            // 设置分页信息
            PageHelper.startPage(page, size);
            List<Map<String, Object>> findAll = commonCompanyMapper.findCompanyByPars(name, phone);
            System.out.println(findAll.get(0));
            if(null!=findAll &&findAll.size()>0){
                businessMessage.setData(new PageInfo<>(findAll));
                businessMessage.setSuccess(true);
            }else{
                businessMessage.setMsg("获取企业列表失败，请重试");
            }
        }catch(Exception e){
            log.error("获取分页查询信息失败", e);
            businessMessage.setMsg("获取企业列表失败，请重试");
        }
        return businessMessage;
    }

    public BusinessMessage positionNumSort(String name, String phone, Integer page, Integer size) {
        BusinessMessage businessMessage =new BusinessMessage();
        try{
            if(null==page|| page <1){
                page=1;
            }
            if(null==size || size <1){
                size=10;
            }
            // 设置分页信息
            PageHelper.startPage(page, size);
            List<Map<String, Object>> findAll = commonCompanyMapper.positionNumSort(name, phone);
            System.out.println(findAll.get(0));
            if(null!=findAll &&findAll.size()>0){

                businessMessage.setData(new PageInfo<>(findAll));
                businessMessage.setSuccess(true);
            }else{
                businessMessage.setMsg("获取企业列表不存在，请重试");
            }
        }catch(Exception e){
            log.error("获取分页查询信息失败", e);
            businessMessage.setMsg("获取企业列表不存在，请重试");
        }
        return businessMessage;
    }

    public BusinessMessage seeCountNumSort(String name, String phone, Integer page, Integer size) {
        BusinessMessage businessMessage =new BusinessMessage();
        try{
            if(null==page|| page <1){
                page=1;
            }
            if(null==size || size <1){
                size=10;
            }
            // 设置分页信息
            PageHelper.startPage(page, size);
            List<Map<String, Object>> findAll = commonCompanyMapper.seeCountNumSort(name, phone);
            System.out.println(findAll.get(0));
            if(null!=findAll &&findAll.size()>0){
                businessMessage.setData(new PageInfo<>(findAll));
                businessMessage.setSuccess(true);
            }else{
                businessMessage.setMsg("获取企业列表不存在，请重试");
            }
        }catch(Exception e){
            log.error("获取分页查询信息失败", e);
            businessMessage.setMsg("获取企业列表不存在，请重试");
        }
        return businessMessage;
    }

    public BusinessMessage payTimeSort(String name, String phone, Integer page, Integer size) {
        BusinessMessage businessMessage =new BusinessMessage();
        try{
            if(null==page|| page <1){
                page=1;
            }
            if(null==size || size <1){
                size=10;
            }
            // 设置分页信息
            PageHelper.startPage(page, size);
            List<Map<String, Object>> findAll = commonCompanyMapper.payTimeSort(name, phone);
            System.out.println(findAll.get(0));
            if(null!=findAll &&findAll.size()>0){
                businessMessage.setData(new PageInfo<>(findAll));
                businessMessage.setSuccess(true);
            }else{
                businessMessage.setMsg("获取企业列表不存在，请重试");
            }
        }catch(Exception e){
            log.error("获取分页查询信息失败", e);
            businessMessage.setMsg("获取企业列表不存在，请重试");
        }
        return businessMessage;
    }

    /**
     * 读取企业数据
     * @param id
     * @return
     */
    public BusinessMessage findOneByid(Integer id) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            // 校验用户名是否为空
            if (null == id) {
                message.setMsg("主键为空");
            } else {
                User users = this.usersMapper.selectByPrimaryKey(id);
                if(null!=users){
                    HashMap<String, Object> stringObjectHashMap = new HashMap<>();
                    Example example = new Example(Company.class);
                    example.createCriteria().andEqualTo("userId", id);
                    List<Company> companyList = this.companyMapper.selectByExample(example);
                    if (companyList.size()>0) {
//                        stringObjectHashMap.put("company_name",companyList.get(0).getCompanyName());
                        stringObjectHashMap.put("company_scale",companyList.get(0).getCompanyScale());
                        stringObjectHashMap.put("company_special",companyList.get(0).getCompanyScale());
                        stringObjectHashMap.put("company_city",companyList.get(0).getCompanyCity());
                        stringObjectHashMap.put("company_info",companyList.get(0).getCompanyInfo());
                        stringObjectHashMap.put("company_photo",companyList.get(0).getCompanyPhoto());
//                        stringObjectHashMap.put("company_addrx",companyList.get(0).getCompanyAddrx());
//                        stringObjectHashMap.put("company_addry",companyList.get(0).getCompanyAddry());
                        stringObjectHashMap.put("company_addr",companyList.get(0).getCompanyAddr());
                        stringObjectHashMap.put("company_type",companyList.get(0).getCompanyType());
//                        stringObjectHashMap.put("hot",companyList.get(0).getHot());
//                        stringObjectHashMap.put("icon",companyList.get(0).getIcon());
//                        stringObjectHashMap.put("company_fu_ze_ren",companyList.get(0).getCompanyFuZeRen());
                        stringObjectHashMap.put("zhi_wu",companyList.get(0).getZhiWu());
                        stringObjectHashMap.put("see_count",companyList.get(0).getSeeCount());
                        stringObjectHashMap.put("province_id",companyList.get(0).getProvinceId());
                        stringObjectHashMap.put("city_id",companyList.get(0).getCityId());
                        stringObjectHashMap.put("area_id",companyList.get(0).getAreaId());
//                        stringObjectHashMap.put("icon",companyList.get(0).getIcon());
                        stringObjectHashMap.put("company_id",companyList.get(0).getId());
                    }
//                    stringObjectHashMap.put("name",users.getName());
                    stringObjectHashMap.put("phone",users.getPhone());
                    // 设置业务数据
                    message.setData(stringObjectHashMap);
                }
                // 设置业务处理结果
                message.setSuccess(true);
            }
        } catch (Exception e) {
            log.error("查询信息失败", e);
        }
        return message;
    }

    /**
     * 更新企业信息
     * @param id
     * @param company_name
     * @param company_scale
     * @param company_special_str
     * @param province_id
     * @param city_id
     * @param area_id
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
    public BusinessMessage update(Integer id, String company_name, String company_scale, String company_special_str, Integer province_id, Integer city_id, Integer area_id, Integer pid, String company_info, MultipartFile company_photo,MultipartFile icon, String company_addrx, String company_addry, String company_addr, String company_type, String company_city) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            //用户信息
            Example example = new Example(Company.class);
            example.createCriteria().andEqualTo("userId", id);
            List<Company> companyList = this.companyMapper.selectByExample(example);
            if (companyList.size()>0) {
                pid=companyList.get(0).getId();
                //更新用户信息
                Company company =companyMapper.selectByPrimaryKey(pid);
                String photoName = company.getCompanyPhoto();
//                String iconName = company.getIcon();
                if (icon != null) {
                    String pathCheckPath2 = environment.getProperty("api.fileImagePath");
                    File hashFile = new File(pathCheckPath2);
                    //没有就创建
                    if (!hashFile.exists()) {
                        hashFile.mkdirs();
                    }
                    String photoName1 = reamNameFile(icon, pathCheckPath2);
                    if(!StringUtils.isEmpty(photoName1)) {
//                        iconName = environment.getProperty("api.ImageSrc") + "/" + photoName1;
                    }
//                    log.debug("上传Logo图片：" + iconName);
                }
                if (company_photo != null) {
                    String pathCheckPath = environment.getProperty("api.fileImagePath");
                    File hashFile = new File(pathCheckPath);
                    //没有就创建
                    if (!hashFile.exists()) {
                        hashFile.mkdirs();
                    }
                    String photoName2 = reamNameFile(company_photo, pathCheckPath);
                    if(!StringUtils.isEmpty(photoName2)) {
                        photoName = environment.getProperty("api.ImageSrc") + "/" + photoName2;
                    }
                    log.debug("上传图片：" + photoName);
                }

//                company.setCompanyName(company_name);
                company.setCompanyScale(company_scale);
                company.setCompanySpecial(company_special_str);
                company.setProvinceId(province_id);
                company.setCityId(city_id);
                company.setAreaId(area_id);
                company.setCompanyInfo(company_info);
//                company.setIcon(iconName);
                company.setCompanyPhoto(photoName);
//                company.setCompanyAddrx(company_addrx);
//                company.setCompanyAddry(company_addry);
                company.setCompanyAddr(company_addr);
                company.setCompanyType(company_type);
                company.setCompanyCity(company_city);
                this.companyMapper.updateByPrimaryKeySelective(company);
            }else{
                //添加用户信息
                Company company=new Company();

                String iconName = "";
                if (icon != null) {
                    String pathCheckPath3 = environment.getProperty("api.fileImagePath");
                    File hashFile = new File(pathCheckPath3);
                    //没有就创建
                    if (!hashFile.exists()) {
                        hashFile.mkdirs();
                    }
                    String photoName3 = reamNameFile(icon, pathCheckPath3);
                    if(!StringUtils.isEmpty(photoName3)) {
                        iconName = environment.getProperty("api.ImageSrc") + "/" + photoName3;
                    }
                    log.debug("上传Logo图片：" + iconName);
                }

                String photoName = "";
                if (company_photo != null) {
                    String pathCheckPath4 = environment.getProperty("api.fileImagePath");
                    File hashFile = new File(pathCheckPath4);
                    //没有就创建
                    if (!hashFile.exists()) {
                        hashFile.mkdirs();
                    }
                    photoName = reamNameFile(company_photo, pathCheckPath4);
                    log.debug("上传图片：" + photoName);
                }
                company.setUserId(id);
//                company.setCompanyName(company_name);
                company.setCompanyScale(company_scale);
                company.setCompanySpecial(company_special_str);
                company.setProvinceId(province_id);
                company.setCityId(city_id);
                company.setAreaId(area_id);
                company.setCompanyInfo(company_info);
//                company.setIcon(iconName);
                company.setCompanyPhoto(photoName);
//                company.setCompanyAddrx(company_addrx);
//                company.setCompanyAddry(company_addry);
                company.setCompanyAddr(company_addr);
                company.setCompanyType(company_type);
                company.setCompanyCity(company_city);
                this.companyMapper.insertSelective(company);
            }

            // 设置业务处理结果
            message.setSuccess(true);

        } catch (Exception e) {
            log.error("更新求职者信息失败", e);
        }
        return message;
    }
    /**
     * 更新照片
     * @param id
     * @param image_url
     * @return
     */
    public BusinessMessage updatePhoto(Integer id, MultipartFile image_url, String photo) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            if (image_url != null) {
                Company company =companyMapper.selectByPrimaryKey(id);
                String photoName = "";
                String pathCheckPath = environment.getProperty("api.fileImagePath");
                File hashFile = new File(pathCheckPath);
                //没有就创建
                if (!hashFile.exists()) {
                    hashFile.mkdirs();
                }
                photoName = reamNameFile(image_url, pathCheckPath);
                if(!StringUtils.isEmpty(photoName)) {
                    photoName=environment.getProperty("api.ImageSrc")+"/"+photoName;
                    String midPhoto = "," + company.getCompanyPhoto() + ",";
                    midPhoto = midPhoto.replace("," + photo + ",", "," + photoName + ",");
                    company.setCompanyPhoto(midPhoto.substring(1, midPhoto.length() - 1));

                    this.companyMapper.updateByPrimaryKeySelective(company);
                }
            }
            // 设置业务处理结果
            message.setSuccess(true);

        } catch (Exception e) {
            log.error("更新广告信息失败", e);
        }
        return message;
    }

    /**
     * 添加照片
     * @param image_url
     * @return
     */
    public BusinessMessage insertPhoto(Integer id,MultipartFile image_url) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            //基本信息
            if (image_url != null) {
                Company company =companyMapper.selectByPrimaryKey(id);
                String photoName = "";
                String pathCheckPath = environment.getProperty("api.fileImagePath");
                File hashFile = new File(pathCheckPath);
                //没有就创建
                if (!hashFile.exists()) {
                    hashFile.mkdirs();
                }
                photoName = reamNameFile(image_url, pathCheckPath);
                if(!StringUtils.isEmpty(photoName)) {
                    photoName=environment.getProperty("api.ImageSrc")+"/"+photoName;
                    if (StringUtils.isEmpty(company.getCompanyPhoto())) {
                        company.setCompanyPhoto(photoName);
                    } else {
                        company.setCompanyPhoto(company.getCompanyPhoto() + "," + photoName);
                    }
                    this.companyMapper.updateByPrimaryKeySelective(company);
                }
            }
            // 设置业务处理结果
            message.setSuccess(true);

        } catch (Exception e) {
            log.error("添加广告信息失败", e);
        }
        return message;
    }
    public String reamNameFile(MultipartFile file, String hashFile) throws IOException {
        //获取后缀
        String filename = file.getOriginalFilename();
        String prefix = filename.substring(filename.lastIndexOf(".") + 1);
        String targetFileName = null;
        if(!StringUtils.isEmpty(prefix)){
            targetFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + prefix;
            File targetFile = new File(hashFile, targetFileName);
            file.transferTo(targetFile);
        }
        return targetFileName;
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
    public BusinessMessage getAllCompanyState(String name,String phone,Integer page,Integer size){
        BusinessMessage businessMessage =new BusinessMessage();
        try{
            if(null==page|| page <1){
                page=1;
            }
            if(null==size || size <1){
                size=10;
            }
            // 设置分页信息
            PageHelper.startPage(page, size);
            List<Map<String,Object>> companyState =commonCompanyMapper.getAllCompanyState(name,phone);
            System.out.println(companyState.get(0));
            if(null!=companyState && companyState.size()>0){
                businessMessage.setData(new PageInfo<>(companyState));
                businessMessage.setMsg("获取审核企业列表成功");
                businessMessage.setSuccess(true);
            }else{
                businessMessage.setMsg("获取企业列表不存在，请重试");
            }
        }catch(Exception e){
            log.error("获取分页查询信息失败", e);
            businessMessage.setMsg("获取企业列表不存在，请重试");
        }
        return businessMessage;
    }

    public BusinessMessage audit(Integer id){
        BusinessMessage businessMessage =new BusinessMessage();
        return businessMessage;
    }
}
