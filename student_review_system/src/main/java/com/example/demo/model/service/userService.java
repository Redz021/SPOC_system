package com.example.demo.model.service;

import com.example.demo.model.ValueObject.UserLogin;
import com.example.demo.model.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.ValueObject.*;

import java.util.List;

@Service
public class userService {
    @Autowired
    private UserMapper userMapper;//mybatis mapper
    public UserLogin getOneUser(String id)
    {
       return userMapper.Sel(id);
    }

    public Student getStuInfo(UserLogin user)
    {
            return userMapper.studentInfo(user.getId());
    }
    public Teacher getTeacherInfo(UserLogin user)
    {
        return userMapper.TeacherInfo(user.getId());
    }
    //新增用户
    public void addUser(UserLogin userLogin)
    {
        userMapper.addUser(userLogin);
    }
    //批量新增用户
    public void addUsers(List<UserLogin> userLogins)
    {
        userMapper.addUsers(userLogins);
    }
    //更新用户
    public void updateUser(UserLogin userLogin,String oldId)
    {
        userMapper.updateUser(userLogin,oldId);
    }
    //删除用户
    public void deleteUser(String id)
    {
        userMapper.deleteUser(id);
    }
    //更改密码
    public void ChangePassword(String userId,String password)
    {
        userMapper.ChangePassword(userId, password);
    }
}
