package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password) {

        //查询用户
        User u = userService.findByUserName(username);
        if (u == null) {
            //没有占用
            //注册
            userService.register(username, password);
            return Result.success();
        } else {
            //占用
            return Result.error("用户名已被占用");
        }
    }
    @PostMapping("/login")
    public  Result login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据用户名查询用户

        User loginUser = userService.findByUserName(username);
        //判断用户是否存在
        if (loginUser == null) {
            return Result.error("用户名错误");
        }
        //判断密码是否正确 loginUser中的密码是加密的
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            //登陆成功
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return  Result.error("密码错误");

    }
    @GetMapping("/userInfo")
    public Result<User> UserInfo(@RequestHeader(name = "Authorization") String token) {
        //根据用户名查询用户
        Map<String,Object> map =  JwtUtil.parseToken(token);//解析token
        String username = (String) map.get("username");//将Result类型token强制转换成String型
        User user = userService.findByUserName(username);
        return Result.success(user);
    }
}
