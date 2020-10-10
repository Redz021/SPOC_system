package com.example.demo.dao;

import com.example.demo.model.ValueObject.Teacher;
import com.example.demo.model.ValueObject.UserLogin;
import com.example.demo.model.service.TeacherService;
import com.example.demo.model.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TeacherOperation {
    @Autowired
    private userDao userDao;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private userService userService;
    //查询所有老师
    public List<Teacher> getAllTeachers()
    {
        return teacherService.getAllTeachers();
    }
    //新增一个老师
    public boolean addTeacher(String TID,String name,String department,String password)
    {
        if(userDao.ifUserExists(TID))
        {
            return false;
        }
        Teacher teacher = new Teacher();
        UserLogin userLogin = new UserLogin();
        teacher.setTID(TID);
        teacher.setTeacherName(name);
        teacher.setDepartment(department);
        userLogin.setId(TID);
        userLogin.setPassword(password);
        userLogin.setAuthority("teacher");
        teacherService.addTeacher(teacher);
        userService.addUser(userLogin);
        return true;
    }
    //更新一个老师
    public boolean updateTeacher(String TID,String name,String department,String oldTID)
    {
        if(TID.equals(oldTID))
        {
            if(userDao.ifUserExists(oldTID))
            {
                Teacher teacher = new Teacher();
                teacher.setTID(TID);
                teacher.setTeacherName(name);
                teacher.setDepartment(department);
                teacherService.update(teacher,oldTID);
                return true;
            }
        }
        if(userDao.ifUserExists(TID))
        {
            return false;
        }
        Teacher teacher = new Teacher();
        teacher.setTID(TID);
        teacher.setTeacherName(name);
        teacher.setDepartment(department);
        teacherService.update(teacher,oldTID);
        if(TID!=oldTID)
        {
            UserLogin userLogin = new UserLogin();
            userLogin.setId(TID);
            userService.updateUser(userLogin,oldTID);
        }
        return true;
    }
    //删除一个老师
    public void deleteTeacher(String TID)
    {
        teacherService.deleteTeacher(TID);
        userService.deleteUser(TID);
    }
}
