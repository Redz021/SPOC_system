package com.example.demo.dao;

import com.example.demo.model.Entity.commentEntity;
import com.example.demo.model.ValueObject.comment;
import com.example.demo.model.service.commentService;
import com.example.demo.model.service.courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class commentDao {
    @Autowired
    private commentService commentService;

    //获取试题下的所有评论
    public commentEntity getTestQuestionComment(String TqNo)
    {
        commentEntity commentEntity = new commentEntity();
        commentEntity.setCommentList(commentService.getTestQuestionComment(TqNo));
        commentEntity.setState("success");
        return commentEntity;
    }

    //新增一条评论
    public void insertComment( String TqNo, String comment, String userId)
    {
        comment comment1 = new comment();
        comment1.setComment(comment);
        comment1.setUserId(userId);
        comment1.setTqNo(TqNo);
        commentService.insertComment(comment1);
    }
}
