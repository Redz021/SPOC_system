package com.example.demo.Controller;

import com.example.demo.dao.userDao;
import com.example.demo.model.ValueObject.UserLogin;
import com.example.demo.model.ValueObject.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@CrossOrigin
@RestController
public class userController {
    private final static String changePasswordUrl="/course/changePassword";//更改密码
    private final static String resetPasswordUrl = "/admin/resetPassword";//重置任意用户的密码
    @Autowired
    private userDao userDao;
    @RequestMapping(value = changePasswordUrl, produces = "application/json; charset=utf-8")
    public String changePassword(@PathParam("password") String password, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        user  user = (user)session.getAttribute("user");
        userDao.changePassword(user.getId(),password.trim());
        return "{\"state\":\"success\"}";
    }
    @RequestMapping(value = resetPasswordUrl+"/{userId}", produces = "application/json; charset=utf-8")
    public String resetUserPassword(@PathVariable String userId)
    {
        if(userDao.resetUserPassword(userId))
        {
            return "{\"state\":\"success\"}";
        }
        return "{\"state\":null}";
    }
}
