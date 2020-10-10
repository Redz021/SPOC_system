package com.example.demo.dao;

import com.example.demo.model.Entity.professionEntity;
import com.example.demo.model.ValueObject.Student;
import com.example.demo.model.ValueObject.UserLogin;
import com.example.demo.model.service.StudentService;
import com.example.demo.model.service.courseOperatorService;
import com.example.demo.model.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
@Component
public class StudentOperation {
    @Autowired
    private userDao userDao;
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
    public String addStudents(List<LinkedHashMap<String,String>> students)
    {
        List<Student> students1 = new LinkedList<>();
        List<UserLogin> userLogins = new LinkedList<>();
       for(LinkedHashMap<String,String> l:students)
       {
           Student s = new Student();
           UserLogin u =  new UserLogin();
           s.setStuId(l.get("stuId"));
           s.setStuName(l.get("stuName"));
           s.setYear_admission(l.get("year_admission"));
           s.setProfession(l.get("profession"));
           u.setAuthority("student");
           u.setId(l.get("stuId"));
           u.setPassword(l.get("password"));
           students1.add(s);
           userLogins.add(u);
       }

        studentService.addStudents(students1);
        userService.addUsers(userLogins);
        return "{\"state\":\"success\"}";
    }

    //添加一个学生
    public boolean addStudent(String stuId,String stuName,String profession,String year_admission,String password)
    {
        if(userDao.ifUserExists(stuId))
        {
            return false;
        }
        UserLogin userLogin = new UserLogin();
        userLogin.setId(stuId);
        userLogin.setPassword(password);
        userLogin.setAuthority("student");

        Student student = new Student();
        student.setStuId(stuId);
        student.setStuName(stuName);
        student.setProfession(profession);
        student.setYear_admission(year_admission);

        studentService.addStudent(student);
        userService.addUser(userLogin);
        return true;
    }
    //更新一个学生信息
    public boolean updateStudent(String stuId,String stuName,String profession,String year_admission,String oldStuId)
    {
        if(stuId.equals(oldStuId))
        {
            if(userDao.ifUserExists(oldStuId))
            {
                Student student = new Student();
                student.setStuId(stuId);
                student.setStuName(stuName);
                student.setProfession(profession);
                student.setYear_admission(year_admission);
                studentService.updateStudent(student,oldStuId);
                return true;
            }
        }

        if(userDao.ifUserExists(stuId))
        {
            return false;
        }
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
        return true;
    }
    //删除一个学生信息
    public void deleteStudent(String stuId)
    {
        studentService.delStudent(stuId);
        userService.deleteUser(stuId);
    }
    @Autowired
    private courseOperatorService courseOperatorService;
    //返回所有已有的学生专业
    public professionEntity getAllProfession()
    {
        professionEntity professionEntity = new professionEntity();
        professionEntity.setProfessions(courseOperatorService.getAllProfession());
        professionEntity.setState("success");
        return professionEntity;
    }
}
