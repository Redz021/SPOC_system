package com.example.demo.dao;

import com.example.demo.model.Entity.courseEntity;
import com.example.demo.model.ValueObject.Course;
import com.example.demo.model.ValueObject.Teacher;
import com.example.demo.model.service.courseOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseOperation {
    @Autowired
    private courseOperatorService courseOperatorService;
    //查询单个课程
    public courseEntity getCourse(String Cno)
    {
        courseEntity courseEntity = new courseEntity();
        courseEntity.setCourse(courseOperatorService.getCourse(Cno));
        courseEntity.setState("success");
        return courseEntity;
    }
    //查询所有课程
    public List<Course> getCourses()
    {
        return courseOperatorService.getCourses();
    }
    //新增课程
    public Course addCourse(String name,String profession,String year_admission,String ifopen)
    {
        Course course = new Course();
        course.setName(name);
        course.setProfession(profession);
        course.setYear_admission(year_admission);
        course.setIfopen(ifopen);
        courseOperatorService.addCourse(course);
        return course;
    }
    //新增教师与课程的关系
    public void addCourseTeacher(String Cno,String TID)
    {
        Course course = new Course();
        course.setCno(Cno);
        course.setTID(TID);
        courseOperatorService.addCourseTeacher(course);
    }
    //更新课程
    public void updateCourse(String Cno,String name,String profession,String year_admission,String ifopen)
    {
        Course course = new Course();
        course.setCno(Cno);
        course.setName(name);
        course.setProfession(profession);
        course.setYear_admission(year_admission);
        course.setIfopen(ifopen);
        courseOperatorService.updateCourse(course);
    }
    //更新课程与教师之间关系
    public void updateCourseTeacher(String Cno,String TID,String oldTID)
    {
        Course course = new Course();
        course.setCno(Cno);
        course.setTID(TID);
        courseOperatorService.updateCourseTeacher(course,oldTID);
    }
    //删除教师与课程的对应关系
    public void deleteCourseTeacher(String Cno,String TID)
    {
        courseOperatorService.deleteCourseTeacher(Cno, TID);
    }
    //删除课程
    public void deleteCourse(String Cno)
    {
        courseOperatorService.deleteCourse(Cno);
    }
}
