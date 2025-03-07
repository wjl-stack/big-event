package com.itheima.controller;


import com.itheima.pojo.Category;
import com.itheima.pojo.Result;
import com.itheima.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//添加文章分类
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;//注入一个service层的实现对象

    @PostMapping
    public Result add(@RequestBody @Validated Category category) {
        //使用service层的方法实现更新
        categoryService.add(category);
        return  Result.success();
    }
}
