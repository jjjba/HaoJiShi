package com.haojishi.controller;

import com.haojishi.security.CurrentUser;
import com.haojishi.security.LoginUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by SongpoLiu on 2017/5/28.
 */
@Controller
public class CommonController {

    /**
     * 根据当前用户角色跳转到各自首页
     *
     * @return
     */
    @GetMapping("/")
    public ModelAndView index(@CurrentUser LoginUser user, HttpSession session, HttpServletRequest request) {
        String viewName = "login";
        // 如果当前登录用户为空，则重定向到首页
        if (null != user && null != user.getRole()) {
            session.setAttribute("user", user);
            session.setAttribute("id", user.getId());
            viewName = "/index";
            switch (user.getRole()) {
                case ROLE_NONE:
                    viewName = "login";
                    break;
            }
        }
        return new ModelAndView(viewName);
    }
}
