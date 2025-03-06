package com.itheima.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import java.time.LocalDateTime;
//lombok在编译阶段为实体类自动生成方法
@Data
public class User {
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore//让springmvc在把当前对象转换成jason字符串时，忽略password，最终的json字符串就没有这个属性
    private String password;//密码
    private String nickname;//昵称
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
