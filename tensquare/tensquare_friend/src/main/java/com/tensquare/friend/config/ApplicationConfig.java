package com.tensquare.friend.config;

import com.tensquare.friend.intercepter.JwtIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by 43967 on 2019/2/25.
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtIntercepter jwtIntercepter;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtIntercepter).
                addPathPatterns("/**").
                excludePathPatterns("/**/login");
    }
}

