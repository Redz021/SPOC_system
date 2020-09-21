package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.dao.commentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class commentController {
    private final static String getCommentsUrl = "/course/testQuestion/getComments";//获取试题下所有的评论
    private final static String insertCommentUrl = "/course/testQuestion/addComment";//添加一条评论


    @Autowired
    private commentDao commentDao;
    @RequestMapping(value=getCommentsUrl,produces = "application/json; charset=utf-8")
    public String getComments(@RequestParam String TqNo)
    {
        return JSON.toJSONString(commentDao.getTestQuestionComment(TqNo));
    }
    @RequestMapping(value=insertCommentUrl,produces = "application/json; charset=utf-8")
    public String insertComment(@RequestParam String TqNo,@RequestParam String comment,@RequestParam String userId)
    {
        commentDao.insertComment(TqNo, comment, userId);
        return "{\"state\":\"success\"}";
    }
}
