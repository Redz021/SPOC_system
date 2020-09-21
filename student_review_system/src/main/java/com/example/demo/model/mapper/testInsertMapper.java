package com.example.demo.model.mapper;

import com.example.demo.model.ValueObject.testPaper;
import com.example.demo.model.ValueObject.testQuestion;
import com.example.demo.model.ValueObject.testQuestionOptions;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface testInsertMapper {
    //插入新的题目并返回TqNo
    void InsertQuestionAndGetTqNo(testQuestion testQuestion);
    //插入题目选项
    void InsertOption(List<testQuestionOptions> t);
    //插入新的试卷
    void InsertTestPaper(testPaper testPaper);
}
