package com.example.demo.model.ValueObject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;


/**
 * 学生做过的试卷
 *
 */
@Data
@EqualsAndHashCode
@ToString
public class TestPaperDone {
    private String TpNo;//试卷号
    private String Dno;//编号
    private String stuID;//学生学号
    private String doDate;//做卷时间
    private List<TestPaperDoneDetails> testPaperDoneDetailsList= new LinkedList<>();
}
