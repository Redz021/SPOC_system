package com.example.demo.model.Entity;

import com.example.demo.model.ValueObject.testQuestion;
import lombok.Data;

import java.util.List;

@Data
public class AllTestQuestionsEntity {
    private String state;//状态
    private List<testQuestion> testQuestions;//课程下的所有试题

}
