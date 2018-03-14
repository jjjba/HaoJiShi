package com.haojishi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haojishi.mapper.BannerMapper;
import com.haojishi.mapper.IndexModuleMapper;
import com.haojishi.model.Banner;
import com.haojishi.model.IndexModule;
import com.haojishi.util.BusinessMessage;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 梁闯
 * @date 2018/03/14 14.41
 */
@Slf4j
@Service
public class BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private Environment environment;

    @Autowired
    private IndexModuleMapper indexModuleMapper;

    /**
     * 获取求职者端所有banner
     * @param page
     * @param size
     * @return BusinessMessage - 所有求职者端banner
     */
    public BusinessMessage getAllPersonalBanner(Integer page, Integer size) {
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
            Example bannerExample = new Example(Banner.class);
            bannerExample.createCriteria().andEqualTo("classification",1);
            bannerExample.setOrderByClause("sort desc");
            List<Banner> bannerList = this.bannerMapper.selectByExample(bannerExample);
            if (bannerList != null && bannerList.size() > 0) {
                businessMessage.setData(new PageInfo<>(bannerList));
                businessMessage.setSuccess(true);
            } else {
                businessMessage.setMsg("求职者banner暂无数据");
                businessMessage.setSuccess(true);
            }
        }catch(Exception e){
            log.error("获取分页查询信息失败", e);
            businessMessage.setMsg("获取求职者banner失败，请重试");
        }
        return businessMessage;
    }

    /**
     * 获取所有企业端banner
     *
     * @param page
     * @param size
     * @return BusinessMessage - 所有企业端banner
     */
    public BusinessMessage getAllCompanyBanner(Integer page, Integer size) {
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
            Example bannerExample = new Example(Banner.class);
            bannerExample.createCriteria().andEqualTo("classification",2);
            bannerExample.setOrderByClause("sort desc");
            List<Banner> bannerList = this.bannerMapper.selectByExample(bannerExample);
            if (bannerList != null && bannerList.size() > 0) {
                businessMessage.setData(new PageInfo<>(bannerList));
                businessMessage.setSuccess(true);
            } else {
                businessMessage.setMsg("企业banner暂无数据");
                businessMessage.setSuccess(true);
            }
        }catch(Exception e){
            log.error("获取分页查询信息失败", e);
            businessMessage.setMsg("获取企业banner失败，请重试");
        }
        return businessMessage;
    }

    /**
     * 获取企业端首页栏目
     *
     * @param page
     * @param size
     * @return BusinessMessage - 企业端首页栏目数据
     */
    public BusinessMessage getIndexModule(Integer page,Integer size){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            if(null==page|| page <1){
                page=1;
            }
            if(null==size || size <1){
                size=10;
            }
            // 设置分页信息
            PageHelper.startPage(page, size);
            Example moduleExample =new Example(IndexModule.class);
            moduleExample.setOrderByClause("sort desc");
            List<IndexModule> indexModuleList =indexModuleMapper.selectByExample(moduleExample);
            if(indexModuleList != null && indexModuleList.size() > 0){
                businessMessage.setMsg("获取企业端首页栏目成功");
                businessMessage.setData(new PageInfo<>(indexModuleList));
                businessMessage.setSuccess(true);
            }
        }catch (Exception e){
            log.error("获取企业端首页栏目失败",e);
            businessMessage.setMsg("获取企业端首页栏目失败");
        }
        return businessMessage;
    }

    /**
     * 根据bannerId获取banner数据
     *
     * @param id
     * @return BusinessMessage - banner数据
     */
    public BusinessMessage getBannerById(Integer id) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            // 校验用户名是否为空
            if (null == id) {
                message.setMsg("bannerId为空");
            } else {
                Banner banner = this.bannerMapper.selectByPrimaryKey(id);
                if(null!=banner) {
                    // 设置业务数据
                    message.setData(banner);
                }
                // 设置业务处理结果
                message.setSuccess(true);
                message.setMsg("获取banner数据成功");
            }
        } catch (Exception e) {
            log.error("查询信息失败", e);
            message.setMsg("获取banner数据失败");
        }
        return message;
    }

    /**
     * 根据indexModuleId 查询indexModule数据
     *
     * @param id
     * @return BusinessMessage - indexModule数据
     */
    public BusinessMessage getIndexModuleById(Integer id){
        BusinessMessage message = new BusinessMessage(false);
        try {
            // 校验用户名是否为空
            if (null == id) {
                message.setMsg("indexModuleId为空");
            } else {
                IndexModule indexModule = indexModuleMapper.selectByPrimaryKey(id);
                if(null!=indexModule) {
                    message.setData(indexModule);
                }
                message.setSuccess(true);
                message.setMsg("获取企业端首页栏目数据成功");
            }
        } catch (Exception e) {
            log.error("获取企业端首页栏目数据失败", e);
            message.setMsg("获取企业端首页栏目数据失败");
        }
        return message;
    }

    /**
     * 更新企业端banner
     *
     * @param id
     * @param image_url
     * @param url
     * @param sorts
     * @return BusinessMessage - 是否更新企业端banner成功信息
     */
    public BusinessMessage updateCompanyBannerById(Integer id, MultipartFile image_url, String url, Integer sorts ,String note) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            //基本信息
            Banner banner = this.bannerMapper.selectByPrimaryKey(id);
            banner.setUrl(url);
            banner.setSort(sorts);
            banner.setClassification(2);
            banner.setNote(note);
            String photoName = banner.getImageUrl();
            if (image_url != null && image_url.getName() !=null && image_url.getName() !="") {
                String pathCheckPath = environment.getProperty("api.fileImagePath");
                File hashFile = new File(pathCheckPath);
                //没有就创建
                if (!hashFile.exists()) {
                    hashFile.mkdirs();
                }
               String photoName1 = reamNameFile(image_url, pathCheckPath);
                if(!StringUtils.isEmpty(photoName1)) {
                    photoName = environment.getProperty("api.ImageSrc") + "/" + photoName1;
                }
                log.debug("上传图片：" + photoName);
            }
            banner.setImageUrl(photoName);
            this.bannerMapper.updateByPrimaryKeySelective(banner);
            // 设置业务处理结果
            message.setSuccess(true);
            message.setMsg("更新企业端banner成功");

        } catch (Exception e) {
            log.error("更新广告信息失败", e);
            message.setMsg("更新企业端banner失败");
        }
        return message;
    }

    /**
     * 更新个人端banner
     *
     * @param id
     * @param image_url
     * @param url
     * @param sorts
     * @return BusinessMessage - 是否更新个人端banner成功信息
     */
    public BusinessMessage updatePersonalBannerById(Integer id, MultipartFile image_url, String url, Integer sorts ,String note) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            //基本信息
            Banner banner = this.bannerMapper.selectByPrimaryKey(id);
            banner.setUrl(url);
            banner.setSort(sorts);
            banner.setClassification(1);
            banner.setNote(note);
            String photoName = banner.getImageUrl();
            if (image_url != null && image_url.getName() !=null && image_url.getName() !="") {
                String pathCheckPath = environment.getProperty("api.fileImagePath");
                File hashFile = new File(pathCheckPath);
                //没有就创建
                if (!hashFile.exists()) {
                    hashFile.mkdirs();
                }
                String photoName1 = reamNameFile(image_url, pathCheckPath);
                if(!StringUtils.isEmpty(photoName1)) {
                    photoName = environment.getProperty("api.ImageSrc") + "/" + photoName1;
                }
                log.debug("上传图片：" + photoName);
            }
            banner.setImageUrl(photoName);
            this.bannerMapper.updateByPrimaryKeySelective(banner);
            // 设置业务处理结果
            message.setSuccess(true);
            message.setMsg("更新企业端banner成功");

        } catch (Exception e) {
            log.error("更新广告信息失败", e);
            message.setMsg("更新企业端banner失败");
        }
        return message;
    }

    /**
     * 更新indexModule数据
     *
     * @param id
     * @return BusinessMessage - 是否成功更新indexModule信息
     */
    public BusinessMessage updateIndexModuleById(Integer id,MultipartFile image_url,String url,Integer sorts ,String note){
        BusinessMessage message = new BusinessMessage(false);
        try {
            //基本信息
            IndexModule indexModule = indexModuleMapper.selectByPrimaryKey(id);
            indexModule.setUrl(url);
            indexModule.setSort(sorts);
            indexModule.setNote(note);
            String photoName = indexModule.getImageUrl();
            if (image_url != null && image_url.getName() !=null && image_url.getName() !="") {
                String pathCheckPath = environment.getProperty("api.fileImagePath");
                File hashFile = new File(pathCheckPath);
                //没有就创建
                if (!hashFile.exists()) {
                    hashFile.mkdirs();
                }
                String photoName1 = reamNameFile(image_url, pathCheckPath);
                if(!StringUtils.isEmpty(photoName1)) {
                    photoName = environment.getProperty("api.ImageSrc") + "/" + photoName1;
                }
                log.debug("上传图片：" + photoName);
            }
            indexModule.setImageUrl(photoName);
            indexModuleMapper.updateByPrimaryKeySelective(indexModule);
            // 设置业务处理结果
            message.setSuccess(true);
            message.setMsg("更新企业端首页栏目成功");

        } catch (Exception e) {
            log.error("更新企业端首页栏目失败", e);
            message.setMsg("更新企业端首页栏目失败");
        }
        return message;
    }

    /**
     * 添加求职者端banner
     *
     * @param image_url
     * @param url
     * @param sorts
     * @return BusinessMessage - 是否添加banner成功信息
     */
    public BusinessMessage insertPersonalBanner(MultipartFile image_url, String url, Integer sorts, String note) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            //基本信息
            Banner banner = new Banner();
            banner.setUrl(url);
            banner.setSort(sorts);
            banner.setCreateTime(new Date());
            banner.setClassification(1);
            banner.setNote(note);
            String photoName = "";
            if (image_url != null) {
                String pathCheckPath = environment.getProperty("api.fileImagePath");
                File hashFile = new File(pathCheckPath);
                //没有就创建
                if (!hashFile.exists()) {
                    hashFile.mkdirs();
                }
                photoName = reamNameFile(image_url, pathCheckPath);
                photoName=environment.getProperty("api.ImageSrc")+"/"+photoName;
                log.debug("上传图片：" + photoName);
            }
            banner.setImageUrl(photoName);
            this.bannerMapper.insertSelective(banner);
            // 设置业务处理结果
            message.setSuccess(true);
            message.setMsg("添加求职者端banner成功");

        } catch (Exception e) {
            log.error("添加广告信息失败", e);
            message.setMsg("添加求职者端banner失败");
        }
        return message;
    }

    /**
     * 添加企业端banner
     *
     * @param image_url
     * @param url
     * @param sorts
     * @return BusinessMessage - 是否添加banner成功信息
     */
    public BusinessMessage insertCompanyBanner(MultipartFile image_url, String url, Integer sorts, String note) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            //基本信息
            Banner banner = new Banner();
            banner.setUrl(url);
            banner.setSort(sorts);
            banner.setCreateTime(new Date());
            banner.setClassification(2);
            banner.setNote(note);
            String photoName = "";
            if (image_url != null) {
                String pathCheckPath = environment.getProperty("api.fileImagePath");
                File hashFile = new File(pathCheckPath);
                //没有就创建
                if (!hashFile.exists()) {
                    hashFile.mkdirs();
                }
                photoName = reamNameFile(image_url, pathCheckPath);
                photoName=environment.getProperty("api.ImageSrc")+"/"+photoName;
                log.debug("上传图片：" + photoName);
            }
            banner.setImageUrl(photoName);
            this.bannerMapper.insertSelective(banner);
            // 设置业务处理结果
            message.setSuccess(true);
            message.setMsg("添加企业端banner成功");

        } catch (Exception e) {
            log.error("添加广告信息失败", e);
            message.setMsg("添加企业端banner失败");
        }
        return message;
    }

    /**
     * 删除banner数据
     *
     * @param id
     * @return BusinessMessage - 是否删除banner成功信息
     */
    public BusinessMessage deleteBannerById(Integer id){
        BusinessMessage businessMessage =new BusinessMessage();
        try {
            Banner banner =bannerMapper.selectByPrimaryKey(id);
            bannerMapper.deleteByPrimaryKey(banner);
            businessMessage.setMsg("删除banner成功");
            businessMessage.setSuccess(true);
        }catch (Exception e){
            log.error("删除banner失败",e);
            businessMessage.setMsg("删除banner失败");
        }
        return businessMessage;
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
}
