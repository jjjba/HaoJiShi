package com.haojishi.security;

import com.haojishi.mapper.MembersMapper;
import com.haojishi.model.Members;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by SongpoLiu on 2017/5/28.
 */
@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    MembersMapper membersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        try {
            Members members = membersMapper.selectOne(new Members() {{
                setUsername(username);
            }});
            if (null != members) {
                userDetails = new LoginUser(members);
            }
        } catch (Exception e) {
            log.error("查询用户失败, 帐号 {}，{}", username, e.getMessage());
        }

        if (userDetails == null) {
            throw new UsernameNotFoundException("帐号" + username + "不存在");
        }
        return userDetails;
    }

}
