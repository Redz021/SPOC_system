package com.example.demo.model.Entity;

import com.example.demo.model.ValueObject.testQuestion;
import com.example.demo.model.ValueObject.testQuestionOptions;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Data
public class testPaperEntity {
    private String state;//状态
    private List<testQuestion> testQuestions =new LinkedList<>();//试卷下所有试题
}
