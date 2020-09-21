package com.example.demo.model.service;

import com.example.demo.model.ValueObject.Course;
import com.example.demo.model.mapper.courseOperatorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class courseOperatorService {
    @Autowired
    private courseOperatorMapper courseOperatorMapper;
    //查询所有课程
    public List<Course> getCourses()
    {
        return courseOperatorMapper.getCourses();
    }
    //添加课程
    public void addCourse(Course course)
    {
        courseOperatorMapper.addCourse(course);
    }
    //添加教师与课程的关系
    public void addCourseTeacher(Course course)
    {
        courseOperatorMapper.addCourseTeacher(course);
    }
    //更改课程
    public void updateCourse(Course course)
    {
        courseOperatorMapper.updateCourse(course);
    }
    //更新教师与课程的关系
    public void updateCourseTeacher(Course course,String oldTID)
    {
        courseOperatorMapper.updateCourseTeacher(course,oldTID);
    }
    //删除老师与课程的对应关系
    public void deleteCourseTeacher(String Cno,String TID)
    {
        courseOperatorMapper.deleteCourseTeacher(Cno, TID);
    }
    //删除课程
    public void deleteCourse(String Cno)
    {
        courseOperatorMapper.deleteCourse(Cno);
    }
}
