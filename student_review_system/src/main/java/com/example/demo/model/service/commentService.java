package com.example.demo.model.service;

import com.example.demo.model.ValueObject.comment;
import com.example.demo.model.mapper.commentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class commentService {
    @Autowired
    private commentMapper commentMapper;
    //获取试题下的所有评论
    public List<comment> getTestQuestionComment(String TqNo)
    {
        return commentMapper.getTestQuestionComment(TqNo);
    }

    //新增一条评论
    public void insertComment(comment comment)
    {
        commentMapper.insertComment(comment);
    }
}
