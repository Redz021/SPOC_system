package com.example.demo.dao;

import com.example.demo.model.Entity.CourseConnectEntity;
import com.example.demo.model.ValueObject.Course;
import com.example.demo.model.ValueObject.Student;
import com.example.demo.model.ValueObject.Teacher;
import com.example.demo.model.service.courseService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
@Data
public class courseConnection {
    @Autowired
    private courseService courseService;//mybatis mapper
    //教师或学生获取所有可见课程
    private List<Course> GetCourses(Object o)
    {

        if(o instanceof Student)
        {
            Student student = (Student)o;
            List<Course>courses = courseService.StudentGetCourses(student.getYear_admission(),student.getProfession());//获取学生的所有有关课程
            Iterator<Course> iter = courses.iterator();
            //课程不可见将被剔除
            while(iter.hasNext())
            {
                if(iter.next().getIfopen().equals("close"))
                {
                    iter.remove();
                }
            }
            return courses;
        }
        else if(o instanceof Teacher)
        {
            Teacher teacher = (Teacher)o;
            List<Course>courses = courseService.TeacherGetCourses(teacher.getTID());
            Iterator<Course> iter = courses.iterator();
            //课程不可见将被剔除
            while(iter.hasNext())
            {
                if(iter.next().getIfopen().equals("close"))
                {
                    iter.remove();
                }
            }
            return courses;
        }
        return null;
    }
    public CourseConnectEntity getCourses(Object o)
    {
        CourseConnectEntity result = new CourseConnectEntity();
        result.setCourses(GetCourses(o));
        if(result.getCourses()!=null&&!result.getCourses().isEmpty())
        {
            result.setState("success");
        }
        return result;
    }
}
