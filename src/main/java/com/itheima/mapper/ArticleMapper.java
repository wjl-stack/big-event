package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {

    //新增
    @Insert("INSERT INTO article(title, content, cover_img, " +
            "category_id, create_user, create_time, update_time) " +
            "values (#{title},#{content},#{coverImg},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);
}
