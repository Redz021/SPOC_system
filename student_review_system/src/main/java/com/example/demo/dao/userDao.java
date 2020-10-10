package com.example.demo.dao;

import com.example.demo.model.ValueObject.UserLogin;
import com.example.demo.model.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class userDao {
    @Autowired
    private userService userService;
    public void changePassword(String userId,String password)
    {
        userService.ChangePassword(userId,password);
    }
    //判断用户是否存在
    public boolean ifUserExists(String userId)
    {
        UserLogin user = userService.getOneUser(userId);
        if(user==null)
        {
            return false;
        }
        return true;
    }
    //重置用户的密码
    public boolean resetUserPassword(String userId)
    {
        if(!ifUserExists(userId))
        {
            return false;
        }
        userService.ChangePassword(userId,"123456");
        return true;
    }
}
