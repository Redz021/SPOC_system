package com.example.demo.Controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.testPaperDao;
import com.example.demo.dao.testQuestionDao;
import com.example.demo.model.Entity.AllTestQuestionsEntity;
import com.example.demo.tools.getCurrentTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class testController {
    /************************对试题的增删改查*********************/
    private final static String GetQuestionDetailUrl="/course/QuestionDetail";//获取试题详细信息
    private final static String courseAllTestQuestionsUrl="/course/allTestQuestions";//获取课程下所有试题的概要信息
    private final static String DeleteQuestion="/teacher/course/deleteQuestion";//删除试题
    private final static String insertNewQuestionUrl = "/teacher/course/addNewQuestion";//在课程下添加试题
    private final static String updateQuestionUrl="/teacher/course/updateQuestion";//更新试题
    /***********************对试卷的增删改查*************************/
    private final static String courseAllTestPaperUrl="/course/allTestPapers";//获取课程下所有试卷的概要信息
    private final static String GetPaperUrl="/course/testPaper";//获取试卷详细信息
    private final static String InsertPaperUrl = "/teacher/course/addTestPaper";//新增一张试卷
    private final static String updatePaperUrl="/teacher/course/updateTestPaper";//更新一张试卷
    private final static String deletePaperUrl="/teacher/course/deleteTestPaper";//删除一张试卷
    @Autowired
    private testQuestionDao assembling;
    @RequestMapping(value=GetQuestionDetailUrl+"/{TqNo}" ,produces = "application/json; charset=utf-8")//produces解决中文乱码问题
    public String GetQuestionDetail(HttpServletRequest request , @PathVariable String TqNo)
    {
        String jsonOutput= JSON.toJSONString(assembling.GetQuestionDetail(TqNo));
        return jsonOutput;
    }
    @Autowired
    private testPaperDao testPaperDao;
    @RequestMapping(value=GetPaperUrl+"/{TpNo}" ,produces = "application/json; charset=utf-8")
    public String GetPaper(HttpServletRequest request , @PathVariable String TpNo)
    {
        String jsonOutput= JSON.toJSONString(testPaperDao.GetPaper(TpNo));
        return jsonOutput;
    }
    @RequestMapping(value=DeleteQuestion+"/{TqNo}" ,produces = "application/json; charset=utf-8")//produces解决中文乱码问题
    public String DeleteQuestion(HttpServletRequest request,@PathVariable String TqNo)
    {
        assembling.deleteQuestion(TqNo);
        return "{\"state\":\"success\"}";
    }
    @RequestMapping(value=courseAllTestPaperUrl+"/{Cno}" ,produces = "application/json; charset=utf-8")//produces解决中文乱码问题
    public String GetAllTestPaperSummary(HttpServletRequest request , @PathVariable String Cno)
    {
        return JSON.toJSONString(testPaperDao.assemblingPapers(Cno.trim()));
    }

    @RequestMapping(value=courseAllTestQuestionsUrl+"/{Cno}" ,produces = "application/json; charset=utf-8")//produces解决中文乱码问题
    public AllTestQuestionsEntity GetAllTestQuestionSummary(HttpServletRequest request , @PathVariable String Cno)
    {
        return assembling.GetAllTestQuestions(Cno.trim());
    }

    @RequestMapping(value=insertNewQuestionUrl+"/{Cno}" ,produces = "application/json; charset=utf-8")//produces解决中文乱码问题
    public String addNewQuestion(@RequestBody JSONObject jsonParam,@PathVariable String Cno)
    {

        String type=(String)jsonParam.get("type");
        String describe=(String)jsonParam.get("describe");
        String describe_pic=(String)jsonParam.get("describe_pic");
        String parsing=(String)jsonParam.get("parsing");
        String parsing_pic=(String)jsonParam.get("parsing_pic");
        String answer=(String)jsonParam.get("answer");
        String answer_pic=(String)jsonParam.get("answer_pic");
        List<HashMap<String, String>> options;
        if(type.equals("SingleSelection")||type.equals("MultipleSelection"))
        {
            options = (List<HashMap<String, String>>) jsonParam.get("options");
        }
        else
        {
            options=null;
        }
        return JSON.toJSONString(assembling.InsertQuestionAndGetTqNo(Cno,type,describe,describe_pic,parsing,parsing_pic,
               answer,answer_pic,getCurrentTime.getTime(),options));
    }
    @RequestMapping(value=updateQuestionUrl+"/{TqNo}" ,produces = "application/json; charset=utf-8")//produces解决中文乱码问题
    public String updateQuestion(@RequestBody JSONObject jsonParam,@PathVariable String TqNo)
    {
        String type=(String)jsonParam.get("type");
        String describe=(String)jsonParam.get("describe");
        String describe_pic=(String)jsonParam.get("describe_pic");
        String parsing=(String)jsonParam.get("parsing");
        String parsing_pic=(String)jsonParam.get("parsing_pic");
        String answer=(String)jsonParam.get("answer");
        String answer_pic=(String)jsonParam.get("answer_pic");
        List<HashMap<String, String>> options;
        if(type.equals("SingleSelection")||type.equals("MultipleSelection"))
        {
            options = (List<HashMap<String, String>>) jsonParam.get("options");
        }
        else
        {
            options=null;
        }
        return JSON.toJSONString(assembling.updateQuestion(TqNo,type,describe,describe_pic,parsing,parsing_pic,
                answer,answer_pic,getCurrentTime.getTime(),options));
    }
    @RequestMapping(value=InsertPaperUrl ,produces = "application/json; charset=utf-8")
    public String InsertPaper(@RequestBody JSONObject jsonParam)
    {
        String Cno = (String)jsonParam.get("Cno");
        String paperName = (String) jsonParam.get("paperName");
        Map<String,String> TqNos = (Map)JSON.parse((String)jsonParam.get("TqNos"));
        return JSON.toJSONString(testPaperDao.InsertPaper(Cno,TqNos,paperName));
    }
    @RequestMapping(value=updatePaperUrl+"/{TpNo}" ,produces = "application/json; charset=utf-8")
    public String updatePaper(@PathVariable String TpNo,@RequestBody JSONObject jsonParam)
    {
        String Cno = (String)jsonParam.get("Cno");
        String paperName = (String) jsonParam.get("paperName");
        Map<String,String> TqNos = (Map)JSON.parse((String)jsonParam.get("TqNos"));
        return JSON.toJSONString(testPaperDao.updatePaper(TpNo,TqNos,paperName));
    }
    @RequestMapping(value=deletePaperUrl+"/{TpNo}" ,produces = "application/json; charset=utf-8")
    public String deletePaper(@PathVariable String TpNo)
    {
        testPaperDao.deletePaper(TpNo);
        return "{\"state\":\"success\"}";
    }
}
