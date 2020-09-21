package com.example.demo.model.ValueObject;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;

/**
 * 选择题的一个选项
 */
@Data
public class testQuestionOptions {
    private String id;//选择题的题号
    private String TqNo;//试题号
    private String option;//A选项T
    private String optionDescription;//选项描述
}
