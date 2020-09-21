package com.example.demo.model.service;

import com.example.demo.model.ValueObject.testPaper;
import com.example.demo.model.ValueObject.testQuestion;
import com.example.demo.model.ValueObject.testQuestionOptions;
import com.example.demo.model.mapper.testUpdateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class testUpdateService {
    @Autowired
    private testUpdateMapper testUpdateMapper;
    @Autowired
    private testDeleteService testDeleteService;
    @Autowired
    private testInsertService testInsertService;
    //题目更新
    public void updateQuestion(testQuestion testQuestion)
    {
        testUpdateMapper.updateQuestion(testQuestion);
    }
    //选项更新（删除再添加）options必须有TqNo信息
    public void updateOptions(List<testQuestionOptions> options,String TqNo)
    {
        testDeleteService.deleteOptions(TqNo);//删除
        testInsertService.InsertOptions(options);//添加
    }
    //更新试卷
    public void updateTestPaper(testPaper testPaper)
    {
        testUpdateMapper.updateTestPaper(testPaper);
    }
}
