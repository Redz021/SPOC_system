package com.example.demo.dao;

import com.example.demo.model.ValueObject.TestPaperDone;
import com.example.demo.model.ValueObject.TestPaperDoneDetails;
import com.example.demo.model.ValueObject.testPaper;
import com.example.demo.model.ValueObject.testQuestion;
import com.example.demo.model.service.courseService;
import com.example.demo.model.service.testPaperDoneService;
import com.example.demo.tools.getCurrentTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class testPaperDoneDao {
    @Autowired
    private testPaperDao testPaperDao;//mybatis mapper

    @Autowired
    private testPaperDoneService testPaperDoneService;

    public testPaper doTestPaper(String TpNo, String stuId, Map<String,String> answer)
    {
        //根据TpNo获取试卷
        testPaper right = new testPaper();
        right.setTestQuestions(testPaperDao.GetPaper(TpNo).getTestQuestions());

        TestPaperDone testPaperDone = new TestPaperDone();
        testPaperDone.setStuID(stuId);
        testPaperDone.setDoDate(getCurrentTime.getTime());
        testPaperDone.setTpNo(TpNo);
        for(testQuestion t:right.getTestQuestions()) {
            TestPaperDoneDetails tmp = new TestPaperDoneDetails();
            tmp.setAnswer(answer.get(t.getTqNo()));
            tmp.setTqNo(t.getTqNo() + "");
            t.setInputAnswer(answer.get(t.getTqNo()));
            if (answer.get(t.getTqNo()).equals(t.getAnswer())) {

                tmp.setIfwrong("0");
                t.setIfWrong("0");

            } else
            {
                tmp.setIfwrong("1");
                t.setIfWrong("1");
            }
            testPaperDone.getTestPaperDoneDetailsList().add(tmp);
        }
        testPaperDoneService.doTestPaper(testPaperDone);
        return right;
    }
}
