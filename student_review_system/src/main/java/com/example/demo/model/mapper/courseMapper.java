package com.example.demo.model.mapper;

import com.example.demo.model.ValueObject.Course;
import com.example.demo.model.ValueObject.testPaper;
import com.example.demo.model.ValueObject.testQuestion;
import com.example.demo.model.ValueObject.testQuestionOptions;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface courseMapper {
    //返回某一专业某一年级学生的所有课程
    List<Course> StudentCourse(@Param("year_admission") String year_admission,@Param("profession") String profession);
    //返回老师教授的所有课程
    List<Course> TeacherCourse(String TID);
    //返回某一试卷下的所有试题(概要)
    List<testQuestion> CourseAllTestQuestions(String Cno);
    //返回课程下的所有试卷(概略信息)【id,名字,试题号】
    List<testPaper> CourseAllTestPapers(String Cno);


    //获取某一道题的详细信息（选择题无选项）
    testQuestion questionDetail(String TqNo);
    //获取选择题的选项详细信息（一道）
    List<testQuestionOptions> OptionsDetail(String TqNo);
    //获取某一张试卷的所有试题号
    String paperAllTqNo(String TpNo);
    //获取多个所有试题的详细信息
    List<testQuestion> questionDetails(List<String> TqNos);
    //获取多个选择题的选项信息
    List<testQuestionOptions> OptionsDetails(List<String> TpNos);
}
