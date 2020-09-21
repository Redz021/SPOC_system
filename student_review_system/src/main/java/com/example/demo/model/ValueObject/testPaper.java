package com.example.demo.model.ValueObject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;

/**
 * 试卷类，由教师指定的题目所生成的试卷
 */
@Data
public class testPaper {
    private String TpNo;//试卷号
    private String paperName;//试卷名称
    private String Cno;//所在课程号
    private String TqNo;//试卷下的所有试题号(下一版本更改)
    private String doneTimes;//试卷被做的次数
    private List<testQuestion> testQuestions = new LinkedList<>();//试卷下的所有试题

}
