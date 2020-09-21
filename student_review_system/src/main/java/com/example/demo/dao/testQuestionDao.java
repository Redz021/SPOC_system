package com.example.demo.dao;

import com.example.demo.model.Entity.AllTestQuestionsEntity;
import com.example.demo.model.Entity.testQuestionEntity;
import com.example.demo.model.ValueObject.testQuestion;
import com.example.demo.model.ValueObject.testQuestionOptions;
import com.example.demo.model.service.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
public class testQuestionDao {
    @Autowired
    private courseService courseService;//mybatis mapper
    @Autowired
    private testInsertService testInsertService;
    @Autowired
    private testUpdateService testUpdateService;
    @Autowired
    private testDeleteService testDeleteService;
    @Autowired
    private testPaperDoneService testPaperDoneService;
    //获取课程下所有试题
    public AllTestQuestionsEntity GetAllTestQuestions(String Cno)
    {
        AllTestQuestionsEntity a = new AllTestQuestionsEntity();
        List<testQuestion> t =courseService.GetAllTestQuestions(Cno);
        List<testQuestion> rates =  testPaperDoneService.getQuestionsRate(t);
        for(testQuestion tq:t)
        {
            for(testQuestion s : rates)
            {
                if(tq.getTqNo()==s.getTqNo())
                {
                    tq.setRate(s.getRate());
                    continue;
                }

            }
            if(tq.getRate()==null)
            {
                tq.setRate("0");
            }
        }
        a.setTestQuestions(t);
        if(a.getTestQuestions()!=null&&!a.getTestQuestions().isEmpty())
        {
            a.setState("success");
        }

        return a;
    }
    //获取某一试题详细信息
    public testQuestionEntity GetQuestionDetail(String TqNo)
    {
        testQuestionEntity tqe = new testQuestionEntity();
        tqe.setQuestion(courseService.OneTestQuestion(TqNo));
        if(tqe.getQuestion()==null)
        {
            return tqe;
        }
        //若为选择题则获取选项
        if(tqe.getQuestion().getType().equals("SingleSelection")||tqe.getQuestion().getType().equals("MultipleSelection"))
        {
            List<testQuestionOptions> options = courseService.options(TqNo);
            tqe.getQuestion().setOptions(options);
        }
        tqe.setState("success");
        return tqe;
    }
    //新增一道试题
    public testQuestionEntity InsertQuestionAndGetTqNo(String Cno,String type,String describe,String describe_pic,
                                                       String parsing,String parsing_pic,String answer,String answer_pic,
                                                       String submission_time,List<HashMap<String,String>> options)
    {
        testQuestion t = testInsertService.InsertQuestionAndGetTqNo(Cno,type,describe,describe_pic,parsing,parsing_pic,answer,answer_pic,submission_time);
        testQuestionEntity result = new testQuestionEntity();
        List<testQuestionOptions> testQuestionOptions = new LinkedList<>();

        if(type.equals("SingleSelection")||type.equals("MultipleSelection"))
        {
            for(HashMap<String,String> s:options)
            {
                    testQuestionOptions tmp = new testQuestionOptions();
                    tmp.setTqNo(t.getTqNo()+"");
                    tmp.setOption(s.get("option"));
                    tmp.setOptionDescription(s.get("optionDescription"));
                    testQuestionOptions.add(tmp);
            }
            testInsertService.InsertOptions(testQuestionOptions);
        }
        result.setQuestion(t);
        result.getQuestion().setOptions(testQuestionOptions);
        result.setState("success");
        return result;

    }
    //更新一道试题
    public testQuestionEntity updateQuestion(String TqNo,String type,String describe,String describe_pic,
                               String parsing,String parsing_pic,String answer,String answer_pic,
                               String submission_time,List<HashMap<String,String>> options)
    {
        testQuestionEntity testQuestionEntity = new testQuestionEntity();
        //组装testQuestion对象
        testQuestion t = new testQuestion();
        t.setTqNo(Integer.parseInt(TqNo));
        t.setType(type);
        t.setDescribe(describe);
        t.setDescribe_pic(describe_pic);
        t.setParsing(parsing);
        t.setParsing_pic(parsing_pic);
        t.setAnswer(answer);
        t.setAnswer_pic(answer_pic);
        t.setSubmission_time(submission_time);
        testUpdateService.updateQuestion(t);//更新题干
        testQuestionEntity.setQuestion(t);
        if(type.equals("SingleSelection")||type.equals("MultipleSelection"))
        {
            for(HashMap<String,String> s:options)
            {
                testQuestionOptions tmp = new testQuestionOptions();
                tmp.setTqNo(t.getTqNo()+"");
                tmp.setOption(s.get("option"));
                tmp.setOptionDescription(s.get("optionDescription"));
                t.getOptions().add(tmp);
            }
            testUpdateService.updateOptions(testQuestionEntity.getQuestion().getOptions(),TqNo);//更新选项
        }
        testQuestionEntity.setState("success");
        return testQuestionEntity;
    }
    //删除试题
    public void deleteQuestion(String TqNo)
    {
        testDeleteService.deleteQuestion(TqNo);
    }
}
