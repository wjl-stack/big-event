package com.itheima.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final String KEY = "itheima";
	
	//接收业务数据,生成token并返回
    public static String genToken(Map<String, Object> claims) {//接受map类型数据，并封装业务数据
        //生成token，并return
        return JWT.create()
                .withClaim("claims", claims)//添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 ))//过期时间
                .sign(Algorithm.HMAC256(KEY));//指定算法，配置密钥
    }

	//接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        //接受string类型token，并验证
        return JWT.require(Algorithm.HMAC256(KEY))//验证token,生成一个解析后的JWT对象
                .build()
                .verify(token)
                .getClaim("claims")//接受业务数据并发送出去
                .asMap();
    }

}
