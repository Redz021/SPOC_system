package com.example.demo.model.Entity;

import com.example.demo.model.ValueObject.testQuestion;
import com.example.demo.model.ValueObject.testQuestionOptions;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Data
public class testQuestionEntity {
    private String state;//状态
    private testQuestion question = new testQuestion();//试题
}
