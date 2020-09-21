package com.example.demo.model.Entity;

import com.example.demo.model.ValueObject.Course;
import lombok.Data;

import java.util.List;

@Data
public class CourseConnectEntity {
    private String state;//状态
    private List<Course> courses;//教师可见课程的概要信息
}
