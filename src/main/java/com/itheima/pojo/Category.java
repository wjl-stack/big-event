package com.itheima.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
@Data
public class Category {
    @NotNull(groups = Update.class)
    private Integer id;//主键ID
    @NotEmpty(groups = {Update.class,Add.class})
    private String categoryName;//分类名称
    @NotEmpty(groups = {Update.class,Add.class})
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private LocalDateTime updateTime;//更新时间


    //若某个校验项没有指定分组，默认属于Default分组
    //分组之间可以继承，A extends B那么A拥有B中的所有校验项
    public  interface Add{

    }
    public  interface Update{

    }
}
