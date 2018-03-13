package com.haojishi.security;

import com.haojishi.common.RoleType;
import com.haojishi.model.Members;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by SongpoLiu on 2017/5/28.
 */
public class LoginUser implements UserDetails {

    private Members members;

    private RoleType role;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    LoginUser(Members members) {
        this.members = members;
        switch (members.getRole()) {
            case 1:
                role = RoleType.ROLE_ADMIN;
                break;
            case 2:
                role = RoleType.ROLE_WORKER;
                break;
            case 3:
                role = RoleType.ROLE_USER;
                break;
            case 4:
                role = RoleType.ROLE_CUSTOMER;
                break;
            case 5:
                role = RoleType.ROLE_AGENT;
                break;
            case 6:
                role = RoleType.ROLE_GOLD;
                break;
            case 7:
                role = RoleType.ROLE_STRATEGY;
                break;
            default:
                role = RoleType.ROLE_NONE;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(role.name());
    }

    @Override
    public String getPassword() {
        return members.getPassword();
    }

    @Override
    public String getUsername() {
        return name == null ? members.getUsername() : name;
    }

    public Integer getId() {
        return members.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }

    public RoleType getRole() {
        return role;
    }
}
