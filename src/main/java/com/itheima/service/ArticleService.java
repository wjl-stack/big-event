package com.itheima.service;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;

public interface ArticleService {
    //新增文章
    void add(Article article);
    //条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
    //更新文章
    void update(Article article);
    //根据id获取文章详情
    Article findById(Integer id);
    //删除文章
    void deleteById(Integer id);
}
