package com.example.demo.Controller;
/*
*
* 已取消
* */
//import com.example.demo.dao.Login;
import com.example.demo.dao.courseConnection;
import com.example.demo.model.Entity.loginEntity;
import com.example.demo.model.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.ValueObject.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
//@CrossOrigin
////用户登录控制器
//@RestController
//public class userLoginController {
//    private final static String loginUpdateUrl="/login";//登陆表单提交
//    @Autowired
//    private Login login;//用户登录
//    @CrossOrigin
//    @PostMapping(value = loginUpdateUrl,produces = "application/json; charset=utf-8")
//    public loginEntity Login(@RequestParam String id, @RequestParam String password , HttpServletRequest request)
//    {
//        //判断是否可登录
//        if(login.ifAccess(id.trim(),password.trim()))
//        {
//            //装配student并写入session
//            if(login.getUser().getAuthority().equals(authority.student.toString()))
//            {
//                Student student = (Student)login.getUserInfo();
//                System.out.println(student.toString());
//                request.getSession().setAttribute("user",student);
//                return login.getLoginEntity();
//            }
//            //装配teacher并写入session
//            if(login.getUser().getAuthority().equals(authority.teacher.toString()))
//            {
//                Teacher teacher = (Teacher)login.getUserInfo();
//                System.out.println(teacher.toString());
//                request.getSession().setAttribute("user",teacher);
//                return login.getLoginEntity();
//            }
//            //装配admin并写入session
//            if(login.getUser().getAuthority().equals(authority.admin.toString()))
//            {
//                admin admin = (admin)login.getUserInfo();
//                System.out.println(admin.toString());
//                request.getSession().setAttribute("user",admin);
//                return login.getLoginEntity();
//            }
//        }
//        //登陆失败
//        return login.getLoginEntity();
//    }
//}
