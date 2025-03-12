package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.pojo.Category;
import com.itheima.pojo.PageBean;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        //补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        //使用TredLocal获取id
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateUser(id);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //1.创建pagebean对象
        PageBean<Article> pb = new PageBean<>();
        //2.开启分页查询 pagehelper,会将这两个参数自动拼接到sql后
        PageHelper.startPage(pageNum, pageSize);
        //3.调用mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as = articleMapper.list(userId,categoryId,state);
        //page中提供了方法，可以获取pagehelper分页查询后得到的总记录条数和当前页数据
        Page<Article> p = (Page<Article>) as;

        //把数据填充到pagebean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
    //更新文章
    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }
    //获取文章详情
    @Override
    public Article findById(Integer id) {
        Article a = articleMapper.findById(id);

        return a;

    }
    //删除文章
    @Override
    @Transactional // 确保删除操作在事务中执行
    public void deleteById(Integer id) {
        articleMapper.deleteById(id);
    }
}
