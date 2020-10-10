package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PageController {
    private final static String mainPage = "mainPage";
    private final static String add_questionPage = "add_question";
    private final static String mod_questionPage = "mod_question";
    private final static String stuquestionPage = "stuquestion";
    private final static String do_paperPage = "dopaper";
    private final static String add_coursePage="add_course";
    private final static String userPage = "user";
    private final static String adminPage = "admin";
    private final static String mod_coursePage = "mod_course";
    private final static String my_collectionsPage = "my_collections";


    @RequestMapping("/course/mainPage")
    public String getMainPage()
    {
        return mainPage;
    }
    @RequestMapping("/teacher/course/add_question")
    public String add_question()
    {
        return add_questionPage;
    }
   @RequestMapping("/teacher/course/mod_question")
   public String mod_question()
   {
       return mod_questionPage;
   }
    @RequestMapping("/course/do_paperPage")
    public String do_paper()
    {
        return do_paperPage;
    }
    @RequestMapping("/admin/course/add_course")
    public String add_course(){return add_coursePage;}
    @RequestMapping("/admin")
    public String admin(){return adminPage;}
    @RequestMapping("/admin/user")
    public String user() { return userPage; }
    @RequestMapping("/admin/course/mod_course")
    public String mod_course(){return mod_coursePage;}
    @RequestMapping("/course/my_collections")
    public String my_collections(){return my_collectionsPage;}
    @RequestMapping("/course/stuquestion")
    public String stuquestion(){return stuquestionPage;}
}
