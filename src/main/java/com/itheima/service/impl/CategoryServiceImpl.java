package com.itheima.service.impl;

import com.itheima.mapper.CategoryMapper;
import com.itheima.pojo.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service//将CategoryServiceImpl实现类对象交给ioc容器
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    //注入一个mapper对象
    private CategoryMapper categoryMapper;

    //添加文章分类
    @Override
    public void add(Category category) {

        //补充属性值
        category.setCreateTime(LocalDateTime.now());//创建时间
        category.setUpdateTime(LocalDateTime.now());//更新时间

        Map<String,Object> map =  ThreadLocalUtil.get();//通过Treadlocal获取参数
        Integer userid = (Integer) map.get("id");//调用get方法获取id
        category.setCreateUser(userid);
        categoryMapper.add(category);
    }
}
