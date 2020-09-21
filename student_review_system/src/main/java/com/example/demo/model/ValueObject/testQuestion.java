package com.example.demo.model.ValueObject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

/**
 * 题目类，所有的题目都共有的信息
 */
@Data
public class testQuestion {
    private String Cno;//课程号
    private int TqNo;//试题号
    private String type;//类型（大题，单选，多选）
    private String describe;//题干描述
    private String parsing;//题目解析
    private String answer;//题目答案
    private String describe_pic;//题干图片所在位置
    private String parsing_pic;//解析图片所在位置
    private String answer_pic;//答案图片所在位置
    private String submission_time;//题目最后更改时间
    private String inputAnswer;//学生答卷填入的答案
    private String ifWrong;//判断正误
    private String rate;//试题错误率
    List<testQuestionOptions> options = new LinkedList<>();//对应选项
}
