package com.example.demo.model.mapper;

import com.example.demo.model.ValueObject.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    //添加学生
    void addStudent(Student student);
    //批量添加学生
    void addStudents(List<Student>students);
    //更新学生
    void updateStudent(@Param("student") Student student,@Param("oldStuId")String oldStuId);
    //删除学生
    void delStudent(String stuId);
    //查询所有学生
    List<Student> SelStudents();
    //按专业和入学年级查询学生
    List<Student> SelStudentsWhere(@Param("profession")String profession, @Param("year_admission")String year_admission);
}
