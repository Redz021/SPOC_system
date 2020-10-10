package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.testPaperDoneDao;
import com.example.demo.model.service.testPaperDoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
public class testPaperDoneController {
    private final static String doPaperUrl="/course/student/doTestPaper";

    @Autowired
    private testPaperDoneDao testPaperDoneDao;

    @PostMapping (value = doPaperUrl, produces = "application/json; charset=utf-8")
    public String doPaper(@RequestBody JSONObject jsonParam)
    {
        String stuId = (String)jsonParam.get("stuId");
        String TpNo = (String)jsonParam.get("TpNo");
        //List<Map<String,String>> answers = (List<Map<String,String>>)JSON.parse((String)jsonParam.get("answers"));
        List<LinkedHashMap<String,String>> answers = ( List<LinkedHashMap<String,String>>)jsonParam.get("answers");

        return JSON.toJSONString((testPaperDoneDao.doTestPaper(TpNo,stuId,answers)));
    }
}
