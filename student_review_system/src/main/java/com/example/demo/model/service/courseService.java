package com.example.demo.model.service;

import com.example.demo.model.ValueObject.Course;
import com.example.demo.model.ValueObject.testPaper;
import com.example.demo.model.ValueObject.testQuestion;
import com.example.demo.model.ValueObject.testQuestionOptions;
import com.example.demo.model.mapper.courseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class courseService {
    @Autowired
    private courseMapper courseMapper;//mybatis mapper
    //学生获取可见课程
    public List<Course> StudentGetCourses(String year_admission,String profession)
    {
        return courseMapper.StudentCourse(year_admission,profession);
    }
    //教师获取可见课程
    public List<Course> TeacherGetCourses(String TID)
    {
        return courseMapper.TeacherCourse(TID);
    }
    //获取课程下的所有试卷概要信息
    public List<testPaper> GetAllTestPapersSummary(String Cno)
    {
        return courseMapper.CourseAllTestPapers(Cno);
    }
    //获取课程下所有试题的概要信息
    public List<testQuestion> GetAllTestQuestions(String Cno)
    {
        return courseMapper.CourseAllTestQuestions(Cno);
    }
    //获得一个试题信息（不包含选项信息）
    public testQuestion OneTestQuestion(String TqNo)
    {
        return courseMapper.questionDetail(TqNo);
    }
    //获取一个试题的所有选项信息
    public List<testQuestionOptions>options(String TqNo)
    {
        return courseMapper.OptionsDetail(TqNo);
    }
    //获取一张试卷的所有试题号
    public String paperAllTqNo(String TpNo)
    {
        return courseMapper.paperAllTqNo(TpNo);
    }
    //获取多个试题（不包含选项）
    public List<testQuestion> TestQuestions(List<String> TqNos)
    {
        return courseMapper.questionDetails(TqNos);
    }
    //获取多个试题的选项
    public List<testQuestionOptions> TestQuestionsOptions(List<String> TqNos)
    {
        return courseMapper.OptionsDetails(TqNos);
    }
}
