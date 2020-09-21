package com.example.demo.model.service;

import com.example.demo.model.ValueObject.testPaper;
import com.example.demo.model.ValueObject.testQuestion;
import com.example.demo.model.ValueObject.testQuestionOptions;
import com.example.demo.model.mapper.testInsertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class testInsertService {
    @Autowired
    private testInsertMapper testInsertMapper;
    //插入新的题目并返回TqNo
    public testQuestion InsertQuestionAndGetTqNo(String Cno,String type,String describe,String describe_pic,
                                                 String parsing,String parsing_pic,String answer,String answer_pic,String submission_time)
    {
        testQuestion t = new testQuestion();
        t.setCno(Cno);
        t.setType(type);
        t.setDescribe(describe);
        t.setDescribe_pic(describe_pic);
        t.setParsing(parsing);
        t.setParsing_pic(parsing_pic);
        t.setAnswer(answer);
        t.setAnswer_pic(answer_pic);
        t.setSubmission_time(submission_time);
        testInsertMapper.InsertQuestionAndGetTqNo(t);
        return t;
    }
    public void InsertOptions(List<testQuestionOptions> options)
    {
        testInsertMapper.InsertOption(options);
    }
    //插入新的试卷
    public void InsertTestPaper(testPaper testPaper)
    {
        testInsertMapper.InsertTestPaper(testPaper);
    }
}
