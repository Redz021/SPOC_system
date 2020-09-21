package com.example.demo.model.service;

import com.example.demo.model.ValueObject.TestPaperDone;
import com.example.demo.model.ValueObject.testPaper;
import com.example.demo.model.ValueObject.testQuestion;
import com.example.demo.model.mapper.testPaperDoneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class testPaperDoneService {
    @Autowired
    private testPaperDoneMapper testPaperDoneMapper;
    //学生做卷
    public void doTestPaper(TestPaperDone testPaperDone)
    {
        testPaperDoneMapper.doTestPaper(testPaperDone);
        System.out.println(testPaperDone);
        testPaperDoneMapper.doTestPaperDetails(testPaperDone);
    }
    //返回多个试题的错误率
    public List<testQuestion> getQuestionsRate(List<testQuestion> t)
    {
        return testPaperDoneMapper.getQuestionsRate(t);
    }

    //返回多张试卷的被做的人次
    public List<testPaper> getTestPaperDoneTimes(List<testPaper> t)
    {
        return testPaperDoneMapper.getTestPaperDoneTimes(t);
    }
}
