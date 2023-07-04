package com.nancetide.config;

import com.nancetide.interceptor.CheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.nancetide.interceptor")
public class WebConfig implements WebMvcConfigurer {

    private final CheckInterceptor checkInterceptor;

    @Autowired
    public WebConfig(CheckInterceptor checkInterceptor) {
        this.checkInterceptor = checkInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
    
}
