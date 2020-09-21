package com.example.demo.model.mapper;

import com.example.demo.model.ValueObject.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
    //添加老师
    void addTeacher(Teacher teacher);
    //删除老师
    void delTeacher(String TID);
    //更新老师
    void updateTeacher(@Param("teacher") Teacher teacher,@Param("oldTID")String oldTID);
    //查询所有老师
    List<Teacher> SelTeachers();
}
