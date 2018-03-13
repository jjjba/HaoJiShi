package com.haojishi.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haojishi.mapper.MembersMapper;
import com.haojishi.model.Members;
import com.haojishi.security.LoginUser;
import com.haojishi.util.BusinessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 *
 * @author SongpoLiu
 * @date 2017/5/28
 */
@Slf4j
@Service
public class MembersService {

    @Autowired
    private MembersMapper membersMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 添加用户
     *
     * @param gxMembers 用户信息
     * @return 业务消息
     */
    public BusinessMessage save(LoginUser loginUser, Members gxMembers) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            // 校验用户名是否为空
            if (StringUtils.isEmpty(gxMembers.getUsername())) {
                message.setMsg("用户名为空");
            } else if (StringUtils.isEmpty(gxMembers.getPassword())) {
                message.setMsg("密码为空");
            } else if (null == gxMembers.getRole()) {
                message.setMsg("角色为空");
            } else {
                // 检测帐号是否已存在
                Example example = new Example(Members.class);
                example.createCriteria().andEqualTo("username", gxMembers.getUsername());
                List<Members> gxMembersList = this.membersMapper.selectByExample(example);

                if (null != gxMembersList && gxMembersList.size() > 0) {
                    message.setMsg("帐号已存在");
                } else {
                    // 加密密码
                    gxMembers.setPassword(passwordEncoder.encode(gxMembers.getPassword()));

                    // 创建日期
                    gxMembers.setCreateTime(new Date());

                    gxMembers.setUpdateTime(gxMembers.getCreateTime());

                    // 保存用户信息
                    this.membersMapper.insertSelective(gxMembers);

                    // 设置业务处理结果
                    message.setSuccess(true);
                    //添加操作日志
                    String json = JSON.toJSONString(gxMembers);
                }
            }
        } catch (Exception e) {
            log.error("添加用户信息失败，{}", e.getMessage());
        }
        return message;
    }

    /**
     * 删除用户
     *
     * @param id 主键
     * @return 业务消息
     */
    public BusinessMessage delete(Integer id) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            // 校验用户名是否为空
            if (null == id) {
                message.setMsg("主键为空");
            } else {
                // 保存用户信息
                this.membersMapper.deleteByPrimaryKey(id);

                // 设置业务处理结果
                message.setSuccess(true);
            }
        } catch (Exception e) {
            log.error("删除用户信息失败，{}", e.getMessage());
        }
        return message;
    }

    /**
     * 更新用户
     *
     * @param loginUser 主键
     * @param gxMembers 用户信息
     * @return 业务消息
     */
    public BusinessMessage update(LoginUser loginUser, Members gxMembers) {
        System.out.println("更新用户:loginUser = [" + loginUser + "], gxMembers = [" + gxMembers.getRole()+ "]");
        BusinessMessage message = new BusinessMessage(false);
        try {
            Members updateUser = this.membersMapper.selectByPrimaryKey(gxMembers.getId());
            if (null == updateUser) {
                message.setMsg("用户不存在，请重试");
            } else {
                if (!StringUtils.isEmpty(gxMembers.getPassword())) {
                    // 加密密码
                    updateUser.setPassword(passwordEncoder.encode(gxMembers.getPassword()));
                }

                updateUser.setRole(gxMembers.getRole());
                gxMembers.setUpdateTime(new Date());

                // 更新用户信息
                this.membersMapper.updateByPrimaryKeySelective(updateUser);
                message.setMsg("修改成功");
                // 设置业务处理结果
                message.setSuccess(true);

            }
        } catch (Exception e) {
            log.error("更新用户信息失败，{}", e.getMessage());
        }
        return message;
    }

    /**
     * 查询用户
     *
     * @param id 主键
     * @return 业务消息
     */
    public BusinessMessage findById(Integer id) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            // 校验用户名是否为空
            if (null == id) {
                message.setMsg("主键为空");
            } else {
                // 设置业务数据
                message.setData(this.membersMapper.selectByPrimaryKey(id));

                // 设置业务处理结果
                message.setSuccess(true);
            }
        } catch (Exception e) {
            log.error("删除用户信息失败，{}", e.getMessage());
        }
        return message;
    }

    /**
     * 分页查询
     *
     * @param page 页码
     * @param size 大小
     * @return 业务消息
     */
    public BusinessMessage findAll(Integer page, Integer size) {
        BusinessMessage message = new BusinessMessage(false);
        try {
            // 检测页码
            if (null == page || page < 1) {
                page = 1;
            }

            // 检测容量
            if (null == size || size < 1) {
                size = 10;
            }

            // 设置分页参数
            PageHelper.startPage(page, size);

            // 设置业务数据
            message.setData(new PageInfo<>(this.membersMapper.selectByExample(null)));

            // 设置业务处理结果
            message.setSuccess(true);
        } catch (Exception e) {
            log.error("分页查询用户信息失败，{}", e.getMessage());
        }
        return message;
    }

}
