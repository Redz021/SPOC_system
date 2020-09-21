package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PageController {
    private final static String mainPage = "mainPage";
    private final static String add_questionPage = "add_question";
    private final static String mod_questionPage = "mod_question";

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
}
