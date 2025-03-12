package com.itheima.controller;


import com.itheima.pojo.Article;
import com.itheima.pojo.Category;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.ArticleService;
import com.itheima.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    //新增文章
    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();

    }


    //文章列表（条件分页）
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,//必需
            Integer pageSize,//必需
            @RequestParam(required = false) Integer categoryId,//非必需
            @RequestParam(required = false) String state//非必需
    ){
        PageBean<Article> pb =  articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }

    //更新文章
    @PutMapping
    public Result update(@RequestBody @Validated(Article.Update.class) Article article) {
        articleService.update(article);
        return Result.success();
    }

    //获取文章详情
    @GetMapping("/detail")
    public  Result<Article> detail(Integer id) {
        Article c = articleService.findById(id);
        return Result.success(c);
    }

    //删除文章
    @DeleteMapping
    public Result delete(@RequestParam @Validated(Article.Delete.class) Integer id) {
        articleService.deleteById(id);
        return Result.success();
    }

}
