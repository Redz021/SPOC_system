package com.example.demo;

import com.example.demo.dao.testQuestionDao;
import com.example.demo.model.ValueObject.TestPaperDone;
import com.example.demo.model.ValueObject.TestPaperDoneDetails;
import com.example.demo.model.ValueObject.collections;
import com.example.demo.model.ValueObject.testQuestion;
import com.example.demo.model.service.collectionService;
import com.example.demo.model.service.testPaperDoneService;
import com.example.demo.tools.getCurrentTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
public class test {
    @Autowired
    private collectionService collectionService;
    @Autowired
    private testPaperDoneService testPaperDoneService;
    @Autowired
    private testQuestionDao testQuestionDao;
    @Test
    public void test()
    {
        testQuestionDao.GetAllTestQuestions("1");
    }
}
