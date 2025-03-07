package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    //注册
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

    //登录
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

    //获取用户信息
    @GetMapping("/userInfo")
    public Result<User> UserInfo(@RequestHeader(name = "Authorization") String token) {
        //根据用户名查询用户
//        Map<String,Object> map =  JwtUtil.parseToken(token);//解析token
//        String username = (String) map.get("username");//将Result类型token强制转换成String型
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    //更新用户信息
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);//调用service层中的update方法
        return Result.success();
    }


    //更新头像
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam  @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }


    //更新密码
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params) {
        //校验参数
        String oldPwd =  params.get("old_pwd");
        String newPwd =  params.get("new_pwd");
        String rePwd =  params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {//有一个为空就都不处理
            return Result.error("缺少必要的参数");
        }

        //原密码是否正确
        //调用userService根据用户名拿到原密码，在跟oldpwd比对
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User loginUser = userService.findByUserName(username);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码填写不正确");
        }
        //newpwd和repwd是否一样
        if(!rePwd.equals(newPwd)){
            return Result.error("两次填写的新密码不一样");
        }
        if(newPwd.equals(oldPwd)){
            return Result.error("新密码不能和原密码一致");
        }
        //调用service完成密码更新
        userService.updatePwd(newPwd);
        return Result.success();
    }

}
