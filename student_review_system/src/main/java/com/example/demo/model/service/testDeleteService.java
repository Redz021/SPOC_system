package com.example.demo.model.service;

import com.example.demo.model.mapper.testDeleteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class testDeleteService {
    @Autowired
    private testDeleteMapper testDeleteMapper;
    //删除选项
    public void deleteOptions(String TqNo)
    {
        testDeleteMapper.deleteAllOptions(TqNo);
    }
    //删除试题
    public void deleteQuestion(String TqNo)
    {
        testDeleteMapper.deleteQuestion(TqNo);
    }
    //删除试卷
    public void deletePaper(String TpNo)
    {
        testDeleteMapper.deletePaper(TpNo);
    }
}
