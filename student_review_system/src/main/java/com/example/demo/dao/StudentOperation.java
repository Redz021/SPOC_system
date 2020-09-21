package com.example.demo.dao;

import com.example.demo.model.ValueObject.Student;
import com.example.demo.model.ValueObject.UserLogin;
import com.example.demo.model.service.StudentService;
import com.example.demo.model.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
@Component
public class StudentOperation {
    @Autowired
    private StudentService studentService;//学生操作
    @Autowired
    private userService userService;
    //获取所有学生信息
    public List<Student> getAllStudents()
    {
        return studentService.SelStudents();
    }
    //根据专业以及入学时间获取学生信息
    public List<Student> getStudentsWhere(String profession,String year_admission)
    {
        return studentService.SelStudentsWhere(profession,year_admission);
    }
    //添加多个学生
    public String addStudents(String []stuId,String []stuName,String []profession,String []year_admission,String []password)
    {
        List<Student> students = new LinkedList<>();
        List<UserLogin> userLogins = new LinkedList<>();
       try{
           for(int i=0;i<stuId.length;i++)
           {
               Student student = new Student();
               student.setStuId(stuId[i]);
               student.setStuName(stuName[i]);
               student.setProfession(profession[i]);
               student.setYear_admission(year_admission[i]);
               UserLogin userLogin = new UserLogin();
               userLogin.setId(stuId[i]);
               userLogin.setPassword(password[i]);
               userLogin.setAuthority("student");
               students.add(student);
               userLogins.add(userLogin);
           }
       }
       catch (ArrayIndexOutOfBoundsException a)
       {
           return "{\"state\":null}";
       }
        studentService.addStudents(students);
        userService.addUsers(userLogins);
        return "{\"state\":\"success\"}";
    }
    //添加一个学生
    public void addStudent(String stuId,String stuName,String profession,String year_admission,String password)
    {
        Student student = new Student();
        UserLogin userLogin = new UserLogin();
        student.setStuId(stuId);
        student.setStuName(stuName);
        student.setProfession(profession);
        student.setYear_admission(year_admission);
        userLogin.setId(stuId);
        userLogin.setPassword(password);
        userLogin.setAuthority("student");
        studentService.addStudent(student);
        userService.addUser(userLogin);
    }
    //更新一个学生信息
    public void updateStudent(String stuId,String stuName,String profession,String year_admission,String oldStuId)
    {
        Student student = new Student();
        student.setStuId(stuId);
        student.setStuName(stuName);
        student.setProfession(profession);
        student.setYear_admission(year_admission);
        studentService.updateStudent(student,oldStuId);
        if(stuId!=oldStuId)
        {
            UserLogin userLogin = new UserLogin();
            userLogin.setId(stuId);
            userService.updateUser(userLogin,oldStuId);
        }
    }
    //删除一个学生信息
    public void deleteStudent(String stuId)
    {
        studentService.delStudent(stuId);
        userService.deleteUser(stuId);
    }
}
