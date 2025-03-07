package com.itheima.controller;


import com.itheima.pojo.Category;
import com.itheima.pojo.Result;
import com.itheima.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;//注入一个service层的实现对象

    //添加文章分类
    @PostMapping
    public Result add(@RequestBody @Validated Category category) {
        //使用service层的方法实现更新
        categoryService.add(category);
        return  Result.success();
    }
    //文章分类列表
    @GetMapping
    public Result<List<Category>> list() {
        List<Category> cs =  categoryService.list();
        return Result.success(cs);
    }

    //获取文章分类详情
    //根据文章id获取详情
    @GetMapping("/detail")
    public Result<Category> detail(Integer id) {
        Category c = categoryService.findById(id);
        return Result.success(c);

    }
}
