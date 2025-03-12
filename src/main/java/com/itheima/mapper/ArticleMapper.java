package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.*;

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

    //更新
    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id" +
            "=#{categoryId},update_time=#{updateTime} where id=#{id}")
    void update(Article article);

    //根据id获取文章信息
    @Select("select * from article where id=#{id}")
    Article findById(Integer id);

    //根据id删除文章
    @Delete("delete from article where id=#{id}")
    void deleteById(Integer id);
}
