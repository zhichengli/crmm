package com.zcl.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

public class ParseJwtTest {
    static String s  = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlsI_pqawiLCJpYXQiOjE1ODkyNTI1NjcsImV4cCI6MTU4NzQ2MzE5OSwicm9sZSI6ImFkbWluIn0.fo-c2Hl2kyYHLDB7dGifPXXrxCS9yOZpkWZm1ncr45s";
    public static void main(String[] args) {
        try {
            Claims claims = Jwts.parser().setSigningKey("zclcrmm").parseClaimsJws(s)
                    .getBody();
            System.out.println("用户id："+ claims.getId());
            System.out.println("用户名："+ claims.getSubject());
            System.out.println("用户角色："+ claims.get("role"));
            System.out.println("用户登录时间："+ new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(claims.getIssuedAt()));
            System.out.println("失效时间："+ new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(claims.getExpiration()));

        }catch (ExpiredJwtException e){
            e.printStackTrace();
            System.out.println("已经过期");
        }
      }
}

