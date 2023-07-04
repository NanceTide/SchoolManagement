package com.nancetide.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.nancetide.utils.Jwt;
import com.nancetide.utils.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CheckInterceptor implements HandlerInterceptor {

    @Override
    @CrossOrigin(origins = "*")
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {

        String url = req.getRequestURL().toString();

        if(url.contains("login"))
            return true;

        String jwt = req.getHeader("Token");
        res.setContentType("application/json; charset=utf-8");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Headers",
                "Content-Type,Content-Length, Authorization, Accept, X-Requested-With, token");

        if (req.getMethod().equals("OPTIONS")){
            res.setStatus(200);
            return false;
        }

        if(jwt == null || jwt.length() == 0) {
            String notLogin = JSONObject.toJSONString(Result.error("未登录", null));
            res.getWriter().write(notLogin);
            return false;
        }

        try {
            Jwt.decodeJwt(jwt);
        } catch (Exception e) {
            String notLogin = JSONObject.toJSONString(Result.error("未登录", null));
            res.getWriter().write(notLogin);
            return false;
        }

        return true;
    }

}
