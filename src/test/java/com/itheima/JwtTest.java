package com.itheima;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGen(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");
        //生成jwt的代码
        String token = JWT.create()
                .withClaim("user",claims)//添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis()))//添加过期时间
                .sign(Algorithm.HMAC256("itheima"));//指定算法，配置密钥

        System.out.println(token);
    }

}
