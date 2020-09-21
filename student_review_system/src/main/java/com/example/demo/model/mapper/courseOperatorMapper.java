package com.example.demo.model.mapper;

import com.example.demo.model.ValueObject.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface courseOperatorMapper {
    //查询所有课程
    List<Course> getCourses();
    //添加课程
    void addCourse(Course course);
    //添加课程与老师的关系
    void addCourseTeacher(Course course);
    //更改课程
    void updateCourse(Course course);
    //更新教师与课程的关系
    void updateCourseTeacher(@Param("course") Course course,@Param("oldTID") String oldTID);
    //删除老师与课程的关系
    void deleteCourseTeacher(@Param("Cno") String Cno,@Param("TID")String TID);
    //删除课程
    void deleteCourse(String Cno);
}
