package com.example.demo.model.service;

import com.example.demo.model.ValueObject.Teacher;
import com.example.demo.model.mapper.TeacherMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    //查询所有老师
    public List<Teacher> getAllTeachers()
    {
        return teacherMapper.SelTeachers();
    }
    //新增一个老师
    public void addTeacher(Teacher teacher)
    {
        teacherMapper.addTeacher(teacher);
    }
    //更新一个老师
    public void update( Teacher teacher,String oldTID)
    {
        teacherMapper.updateTeacher(teacher,oldTID);
    }
    //删除一个老师
    public void deleteTeacher(String TID)
    {
        teacherMapper.delTeacher(TID);
    }
}
