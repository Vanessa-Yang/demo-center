package org.example.others.logging;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/5/6 0006 16:51
 **/
@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取到用户标识
        String userNo = getUserNo(request);
        // 把用户 ID 放到 MDC 上下文中
        MDC.put("userId", userNo);
        return super.preHandle(request, response, handler);
    }

    private String getUserNo(HttpServletRequest request) {
        // 通过 SSO 或者 Cookie 或者 Auth 信息获取到 当前登陆的用户信息
        return (String) request.getAttribute("Authorization");
    }
}
