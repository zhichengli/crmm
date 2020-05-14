package com.zcl.qa.interceptor;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        System.out.println("经过拦截器");
        //无论如何都是放行，具体能不能操作还是在具体操作中去操作。
        //拦截器只是负责把有请求头中包含token的令牌进行解析。
        String header = request.getHeader("Authorization");

        if(!StringUtils.isEmpty(header)){
            //如果有Authorization头信息就进来，进行解析
            if(header.startsWith("Bearer ")){
                String token = header.substring(7);
                try{
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if(!StringUtils.isEmpty(roles) && "admin".equals(roles)){
                       request.setAttribute("claims_admin",token);
                    }
                    if(!StringUtils.isEmpty(roles) && "user".equals(roles)){
                        request.setAttribute("claims_user",token);
                    }
                }catch (Exception e){
                    throw new RuntimeException("令牌不正确");
                }finally {

                }
            }
        }
        return true;
    }
}
