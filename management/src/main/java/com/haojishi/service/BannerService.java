package com.haojishi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haojishi.mapper.BannerMapper;
import com.haojishi.model.Banner;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by xzcy-01 on 2017/11/27.
 */
@Slf4j
@Service
public class BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private Environment environment;

    /**
     * 读取广告列表
     * @param page
     * @param size
     * @return
     */
    public BusinessMessage listFindPage(Integer page, Integer size) {
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
            Example example = new Example(Banner.class);
            example.setOrderByClause("sorts");
            List<Banner> bannerList = this.bannerMapper.selectByExample(example);
            if (bannerList != null && bannerList.size() > 0) {
                businessMessage.setData(new PageInfo<>(bannerList));
                businessMessage.setSuccess(true);
            } else {
                businessMessage.setMsg("暂无数据");
                businessMessage.setSuccess(true);
            }
        }catch(Exception e){
            log.error("获取分页查询信息失败", e);
            businessMessage.setMsg("获取广告列表不存在，请重试");
        }
        return businessMessage;
    }

    /**
     * 读取用户数据
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
                Banner banner = this.bannerMapper.selectByPrimaryKey(id);
                if(null!=banner) {
                    // 设置业务数据
                    message.setData(banner);
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
     * 更新
     * @param id
     * @param image_url
     * @param url
     * @param sorts
     * @return
     */
    public BusinessMessage update(Integer id, MultipartFile image_url, String url, Integer sorts) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            //基本信息
            Banner banner = this.bannerMapper.selectByPrimaryKey(id);
            banner.setUrl(url);
            banner.setSort(sorts);
//            banner.setUpdateTime(new Date());
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

        } catch (Exception e) {
            log.error("更新广告信息失败", e);
        }
        return message;
    }

    /**
     * 添加
     * @param image_url
     * @param url
     * @param sorts
     * @return
     */
    public BusinessMessage insert(MultipartFile image_url, String url, Integer sorts) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            //基本信息
            Banner banner = new Banner();
            banner.setUrl(url);
            banner.setSort(sorts);
//            banner.setUpdateTime(new Date());
            banner.setCreateTime(new Date());
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
}
