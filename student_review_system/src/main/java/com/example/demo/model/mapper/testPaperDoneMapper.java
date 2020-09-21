package com.example.demo.model.mapper;

import com.example.demo.model.ValueObject.TestPaperDone;
import com.example.demo.model.ValueObject.testPaper;
import com.example.demo.model.ValueObject.testQuestion;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface testPaperDoneMapper {
    void doTestPaper( TestPaperDone testPaperDone);//学生做卷概要信息插入
    /*上下对象传入同一对象，上一方法调用后返回自增主键用于下面的方法*/
    void doTestPaperDetails(@Param("paper") TestPaperDone testPaperDone);//学生做卷详细信息插入

    //返回多个试题的错误率
    List<testQuestion> getQuestionsRate(List<testQuestion> t);
    //返回多张试卷的被做的人次
    List<testPaper> getTestPaperDoneTimes(List<testPaper> t);

}
