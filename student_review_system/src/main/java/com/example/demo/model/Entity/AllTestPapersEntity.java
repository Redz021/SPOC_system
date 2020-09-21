package com.example.demo.model.Entity;

import com.example.demo.model.ValueObject.testPaper;
import lombok.Data;

import java.util.List;

@Data
public class AllTestPapersEntity {
    private String state;//状态
    private List<testPaper> testPapers;//课程下的所有试卷概要

}
