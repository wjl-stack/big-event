package com.itheima.service;

import com.itheima.pojo.Category;

import java.util.List;

public interface CategoryService {
    //添加文章分类
    void add(Category category);
    //文章分类列表
    List<Category> list();
    //根据id查询分类
    Category findById(Integer id);
    //更新分类
    void update(Category category);
    //删除分类
    void deleteById(Integer id);
}
