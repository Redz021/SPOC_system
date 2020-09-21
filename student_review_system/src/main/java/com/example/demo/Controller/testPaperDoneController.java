package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.testPaperDoneDao;
import com.example.demo.model.service.testPaperDoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class testPaperDoneController {
    private final static String doPaperUrl="/course/student/doTestPaper";

    @Autowired
    private testPaperDoneDao testPaperDoneDao;

    @RequestMapping (value = doPaperUrl, produces = "application/json; charset=utf-8")
    public String doPaper(@RequestBody JSONObject jsonParam)
    {
        String stuId = (String)jsonParam.get("stuId");
        String TpNo = (String)jsonParam.get("TpNo");
        Map<String,String>answer = (Map)JSON.parse((String)jsonParam.get("answer"));
        return JSON.toJSONString((testPaperDoneDao.doTestPaper(TpNo,stuId,answer)));
    }
}
