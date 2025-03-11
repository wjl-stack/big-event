package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    //新增
    @Insert("INSERT INTO article(title, content, cover_img, " +
            "category_id, create_user, create_time, update_time) " +
            "values (#{title},#{content},#{coverImg},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    //条件分页
    List<Article> list(Integer userId, Integer categoryId, String state);
}
