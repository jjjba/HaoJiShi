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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 梁闯
 * @date 2018/03/14 20.11
 */
@Slf4j
@Service
public class CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommonCompanyMapper commonCompanyMapper;

    @Autowired
    private Environment environment;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
        BusinessMessage businessMessage = new BusinessMessage();
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
     * 获取企业照片
     * @param id
     * @return BusinessMessage - 企业照片
     */
    public BusinessMessage getCompanyPhotoById(Integer id) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            // 校验用户名是否为空
            if (null == id) {
                message.setMsg("企业id为空");
            } else {
                Company company =companyMapper.selectByPrimaryKey(id);
                message.setData(company);
                message.setSuccess(true);
                message.setMsg("获取企业照片成功");
            }
        } catch (Exception e) {
            log.error("获取企业照片失败", e);
            message.setMsg("获取企业照片失败");
        }
        return message;
    }

    /**
     * 获取企业数据
     * @param id
     * @return
     */
    public BusinessMessage findOneByid(Integer id) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            // 校验用户名是否为空
            if (null == id) {
                message.setMsg("企业主键为空");
            } else {
                HashMap<String, Object> stringObjectHashMap = new HashMap<>();

                Company company =companyMapper.selectByPrimaryKey(id);
                User user =userMapper.selectByPrimaryKey(company.getUserId());
                stringObjectHashMap.put("id",company.getId());
                stringObjectHashMap.put("company_name",company.getName());
                stringObjectHashMap.put("create_time",sdf.format(company.getCreateTime()));
                stringObjectHashMap.put("month_visits",company.getMonthVisits());
                stringObjectHashMap.put("company_fu_ze_ren",company.getUserName());
                stringObjectHashMap.put("zhi_wu",company.getZhiWu());
                stringObjectHashMap.put("phone",company.getPhone());
                stringObjectHashMap.put("openid",user.getOpenid());
                stringObjectHashMap.put("company_type",company.getCompanyType());
                stringObjectHashMap.put("company_scale",company.getCompanyScale());
                stringObjectHashMap.put("company_addr",company.getCompanyAddr());
                stringObjectHashMap.put("resume_number",company.getPositionCount());
                stringObjectHashMap.put("shareNumber",company.getShareNumber());
                stringObjectHashMap.put("positionNumber",company.getPositionNumber());
                stringObjectHashMap.put("positionExposureNumber",company.getPositionExposureNumber());
                stringObjectHashMap.put("positionSeeNumber",company.getPositionSeeNumber());
                stringObjectHashMap.put("companyAddr",company.getCompanyAddr());
                stringObjectHashMap.put("company_special",company.getCompanySpecial());
                stringObjectHashMap.put("company_city",company.getCompanyCity());
                stringObjectHashMap.put("company_info",company.getCompanyInfo());
                stringObjectHashMap.put("company_photo",company.getCompanyPhoto());
                stringObjectHashMap.put("company_addrx",company.getLongitude());
                stringObjectHashMap.put("company_addry",company.getLatitude());
                stringObjectHashMap.put("matstate",company.getMatstate());
                stringObjectHashMap.put("accountState",user.getAccountState());
                stringObjectHashMap.put("icon",company.getIconPath());
//                stringObjectHashMap.put("province_id",companyList.get(0).getProvinceId());
//                stringObjectHashMap.put("city_id",companyList.get(0).getCityId());
//                stringObjectHashMap.put("area_id",companyList.get(0).getAreaId());
//                stringObjectHashMap.put("company_id",companyList.get(0).getId());

                message.setData(stringObjectHashMap);
                message.setMsg("获取数据成功");
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
     * 添加企业照片
     *
     * @param image_url
     * @return BusinessMessage - 增加企业照片是否成功信息
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
            message.setMsg("添加企业照片成功");
        } catch (Exception e) {
            log.error("添加企业照片失败", e);
            message.setMsg("添加企业照片失败");
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

    /**
     * 企业营业执照审核通过
     *
     * @param id
     * @return BusinessMessage - 企业营业执照审核通过信息
     */
    public BusinessMessage approve(Integer id){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            Company company =companyMapper.selectByPrimaryKey(id);
            company.setMatstate(1);
            companyMapper.updateByPrimaryKeySelective(company);
            businessMessage.setMsg("设置审核通过操作成功");
            businessMessage.setSuccess(true);
        }catch (Exception e){
            log.error("设置审核通过操作失败",e);
            businessMessage.setMsg("设置审核通过操作失败");
        }
        return businessMessage;
    }

    /**
     * 企业营业执照审核未通过
     *
     * @param id
     * @return BusinessMessage - 企业营业执照审核未通过信息
     */
    public BusinessMessage auditFailed(Integer id){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            Company company =companyMapper.selectByPrimaryKey(id);
            company.setMatstate(2);
            companyMapper.updateByPrimaryKeySelective(company);
            businessMessage.setMsg("设置审核通过操作成功");
            businessMessage.setSuccess(true);
        }catch (Exception e){
            log.error("设置审核通过操作失败",e);
            businessMessage.setMsg("设置审核通过操作失败");
        }
        return businessMessage;
    }

    /**
     * 冻结企业账户
     *
     * @param companyCheck
     * @return BusinessMessage - 是否冻结成功信息
     */
    public BusinessMessage frozenAccount(String companyCheck){
        BusinessMessage businessMessage = new BusinessMessage();
        try {
            String[] companylId = companyCheck.split(",");//分割出来的字符数组
            for (int i = 0; i < companylId.length; i++) {
                int id = Integer.parseInt(companylId[i]);
                Company company =companyMapper.selectByPrimaryKey(id);
                int userId =company.getUserId();
                User user =userMapper.selectByPrimaryKey(userId);
                user.setAccountState(2);
                userMapper.updateByPrimaryKeySelective(user);
                businessMessage.setSuccess(true);
                businessMessage.setMsg("冻结企业账户成功");
            }
        } catch (Exception e) {
            log.error("冻结企业账户失败", e);
            businessMessage.setMsg("冻结企业账户失败");
        }
        return businessMessage;
    }

    /**
     * 删除企业账户
     *
     * @param companyCheck
     * @return BusinessMessage - 是否删除成功求职者账户信息
     */
    public BusinessMessage deleteAccount(String companyCheck){
        BusinessMessage businessMessage = new BusinessMessage();
        try {
            String[] companylId = companyCheck.split(",");//分割出来的字符数组
            for (int i = 0; i < companylId.length; i++) {
                int id = Integer.parseInt(companylId[i]);
                Company company =companyMapper.selectByPrimaryKey(id);
                int userId =company.getUserId();
                User user =userMapper.selectByPrimaryKey(userId);
                user.setAccountState(3);
                userMapper.updateByPrimaryKey(user);
                businessMessage.setSuccess(true);
                businessMessage.setMsg("删除企业账户成功");
            }
        } catch (Exception e) {
            log.error("删除求职者账户失败", e);
            businessMessage.setMsg("删除企业账户失败");
        }
        return businessMessage;
    }

    /**
     * 删除企业照片
     *
     * @param photoUrl
     * @return BusinessMessage - 是否成功删除企业照片信息
     */
    public BusinessMessage deleteCompanyPhoto(String photoUrl,Integer id){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            System.out.println(photoUrl);
            Company company =companyMapper.selectByPrimaryKey(id);
            String photo =company.getCompanyPhoto();
            String photos = org.apache.commons.lang3.StringUtils.remove(photo, ","+photoUrl);
            company.setCompanyPhoto(photos);
            companyMapper.updateByPrimaryKeySelective(company);
            businessMessage.setMsg("删除企业照片成功");
            businessMessage.setSuccess(true);
        }catch (Exception e){
            log.error("删除企业照片失败");
            businessMessage.setMsg("删除企业照片失败");
        }
        return businessMessage;
    }
}
