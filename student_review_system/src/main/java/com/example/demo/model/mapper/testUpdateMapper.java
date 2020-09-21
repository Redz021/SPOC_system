package com.example.demo.model.mapper;

import com.example.demo.model.ValueObject.testPaper;
import com.example.demo.model.ValueObject.testQuestion;
import com.example.demo.model.ValueObject.testQuestionOptions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface testUpdateMapper {
    //更改题目
    void updateQuestion(testQuestion testQuestion);
    //更改试卷
    void updateTestPaper(testPaper testPaper);
}
