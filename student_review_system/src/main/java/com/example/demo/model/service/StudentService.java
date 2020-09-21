package com.example.demo.model.service;

import com.example.demo.model.ValueObject.Student;
import com.example.demo.model.mapper.StudentMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    //添加学生
    public void addStudent(Student student)
    {
        studentMapper.addStudent(student);
    }
    //批量添加学生
    public void addStudents(List<Student> students)
    {
        studentMapper.addStudents(students);
    }
    //更新学生
    public void updateStudent(Student student,String oldStuId)
    {
        studentMapper.updateStudent(student,oldStuId);
    }
    //删除学生
    public void delStudent(String stuId)
    {
        studentMapper.delStudent(stuId);
    }
    //查询所有学生
    public List<Student> SelStudents()
    {
        return studentMapper.SelStudents();
    }
    //按专业和入学年级查询学生
    public List<Student> SelStudentsWhere(String profession, String year_admission)
    {
        return studentMapper.SelStudentsWhere(profession,year_admission);
    }
}
