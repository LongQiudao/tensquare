package com.tensquare.friend.intercepter;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 43967 on 2019/2/25.
 */

@Component
public class JwtIntercepter extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            final String token = authHeader.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null){
                if("admin".equals(claims.get("roles"))){
                    request.setAttribute("admin_claims", claims);
                }
                if("user".equals(claims.get("roles"))){
                    request.setAttribute("user_claims", claims);
                }
            }
        }
            return true;
        }
    }
