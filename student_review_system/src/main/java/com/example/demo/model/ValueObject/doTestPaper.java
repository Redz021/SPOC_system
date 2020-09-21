package com.example.demo.model.ValueObject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.LinkedList;


/**
 * 学生做过的试卷
 *
 */
@Data
@EqualsAndHashCode
@ToString
public class doTestPaper {
    private testPaper TestPaper;//试卷
    private String Dno;//编号
    private String stuID;//学生学号
    private String doDate;//做卷时间
    private LinkedList<String> answer  =new LinkedList<String>();//学生给出的答案
    private LinkedList<String> wrong = new LinkedList<String>();//错误的题目号（若无法自动判断，该项需要手动填入）
}
