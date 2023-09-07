package com.nancetide.controller;

import com.nancetide.entity.User;
import com.nancetide.service.UserService;
import com.nancetide.utils.Jwt;
import com.nancetide.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public Result<?> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.login(username, password);
        if(user != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", user.getUsername());
            claims.put("access", user.getAccess());
            String jwt = Jwt.generateJwt(claims);
            return Result.success("登录成功", jwt);
        }
        return Result.error("用户名或密码错误", null);
    }

    @RequestMapping("/check")
    public Result<?> check(@RequestHeader String token) {
        Short access = Jwt.jwtToAccess(token);
        return Result.success("已登录", access);
    }

}
