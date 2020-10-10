package com.example.demo.model.mapper;


import com.example.demo.model.ValueObject.Student;
import com.example.demo.model.ValueObject.Teacher;
import com.example.demo.model.ValueObject.UserLogin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    UserLogin Sel(String id);//根据id装配UserLogin
    Student studentInfo(String id);//返回student信息
    Teacher TeacherInfo(String id);//返回teacher信息
    //新增用户
    void addUser(UserLogin userLogin);
    //更新用户
    void updateUser(@Param("user") UserLogin userLogin,@Param("oldId")String oldId);
    //批量增加用户
    void addUsers(List<UserLogin> userLogin);
    //删除用户
    void deleteUser(String id);

    //更改密码
    void ChangePassword(@Param("userId")String userId,@Param("password")String password);
}
