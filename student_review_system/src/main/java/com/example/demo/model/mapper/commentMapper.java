package com.example.demo.model.mapper;

import com.example.demo.model.ValueObject.comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface commentMapper {
    //获取试题下的所有评论
    List<comment> getTestQuestionComment(String TqNo);
    //新增一条评论
    void insertComment(comment comment);
}
