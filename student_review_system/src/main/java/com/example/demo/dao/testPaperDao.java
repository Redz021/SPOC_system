package com.example.demo.dao;


import com.example.demo.model.Entity.AllTestPapersEntity;
import com.example.demo.model.Entity.testPaperEntity;
import com.example.demo.model.ValueObject.testPaper;
import com.example.demo.model.ValueObject.testQuestion;
import com.example.demo.model.ValueObject.testQuestionOptions;
import com.example.demo.model.service.*;
import com.example.demo.tools.JsonArrayParsing;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
public class testPaperDao {
    @Autowired
    private courseService courseService;//mybatis mapper
    @Autowired
    private testPaperDoneService testPaperDoneService;
    //获取课程下所有试题的详细信息
    public AllTestPapersEntity assemblingPapers(String Cno)
    {
        AllTestPapersEntity a = new AllTestPapersEntity();
        List<testPaper>testPapers = courseService.GetAllTestPapersSummary(Cno);
        if(testPapers!=null&&!testPapers.isEmpty())
        {
            List<testPaper>t = testPaperDoneService.getTestPaperDoneTimes(testPapers);//获取试卷被多少人做过
            if(t!=null&&!t.isEmpty())
            {
                for(testPaper tp:testPapers)
                {
                    for(testPaper s:t)
                    {
                        if(s.getTpNo().equals(tp.getTpNo()))
                        {
                            tp.setDoneTimes(s.getDoneTimes());
                        }
                    }
                    if(tp.getDoneTimes()==null||tp.getDoneTimes().isEmpty())
                    {
                        tp.setDoneTimes("0");
                    }
                }
            }
        }


        a.setTestPapers(testPapers);
        if(a.getTestPapers()!=null&&!a.getTestPapers().isEmpty())
        {
            a.setState("success");
        }
        return a;
    }
    //获取一张试卷的详细信息
    public testPaperEntity GetPaper(String TpNo)
    {
        testPaperEntity testPaperEntity = new testPaperEntity();//最终结果
        String allTqNo = courseService.paperAllTqNo(TpNo);
        if(allTqNo==null||allTqNo.isEmpty())
        {
            return testPaperEntity;
        }

        List<String> TqNos = JsonArrayParsing.parsing(allTqNo);//json解析转换成list
        List<testQuestion> tq= courseService.TestQuestions(TqNos);//获取所有题目（不带选项）
        //若未获取到题目信息
        if(tq==null)
        {
            return testPaperEntity;
        }
        List<testQuestionOptions> options = courseService.TestQuestionsOptions(TqNos);//获取所有选项
        //无选择题
        if(options==null)
        {
            testPaperEntity.setTestQuestions(tq);
        }
        //有选择题
        else{
            //在所有试题中寻找
            for(testQuestion t:tq)
            {
                //若为选择题
                if(t.getType().equals("SingleSelection")||t.getType().equals("MultipleSelection")) {
                    for (testQuestionOptions s : options) {
                        //如果选项与题目对应
                        if (String.valueOf(t.getTqNo()).equals(s.getTqNo())) {

                            t.getOptions().add(s);
                        }
                    }

                }
                testPaperEntity.getTestQuestions().add(t);
            }

        }
        testPaperEntity.setState("success");
        return  testPaperEntity;

    }
    @Autowired
    private testInsertService testInsertService;
    //插入一张新的试卷
    public testPaper InsertPaper(String Cno, List<LinkedHashMap<String, String>> TqNos, String paperName)
    {
        testPaper testPaper = new testPaper();
        testPaper.setCno(Cno);
        String tmp="[";
        for(Map<String,String> m:TqNos)
        {
            for(String value : m.values()){
                tmp+=value;
                tmp+=",";
            }

        }
        tmp=tmp.substring(0,tmp.length()-1);
        tmp+="]";
        testPaper.setTqNo(tmp);
        testPaper.setPaperName(paperName);
        testInsertService.InsertTestPaper(testPaper);
        return testPaper;
    }
    @Autowired
    private testUpdateService testUpdateService;
    @Autowired
    private testDeleteService testDeleteService;
    //更新试卷
    public testPaper updatePaper(String TpNo,List<LinkedHashMap<String, String>> TqNos,String paperName)
    {
        testPaper testPaper = new testPaper();
        testPaper.setTpNo(TpNo);
        String tmp="[";
        for(Map<String,String> m:TqNos)
        {
            for(String value : m.values()){
                tmp+=value;
                tmp+=",";
            }

        }
        tmp=tmp.substring(0,tmp.length()-1);
        tmp+="]";
        testPaper.setTqNo(tmp);
        testPaper.setPaperName(paperName);
        testUpdateService.updateTestPaper(testPaper);
        return testPaper;
    }
    //删除一张试卷
    public void deletePaper(String TpNo)
    {
        testDeleteService.deletePaper(TpNo);
    }
}
