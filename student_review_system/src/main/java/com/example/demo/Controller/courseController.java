package com.example.demo.Controller;


import com.alibaba.fastjson.JSON;
import com.example.demo.dao.CourseOperation;
import com.example.demo.dao.courseConnection;
import com.example.demo.dao.testPaperDao;
import com.example.demo.dao.testQuestionDao;
import com.example.demo.model.Entity.AllTestPapersEntity;
import com.example.demo.model.Entity.AllTestQuestionsEntity;
import com.example.demo.model.Entity.CourseConnectEntity;
import com.example.demo.model.Entity.testQuestionEntity;
import com.example.demo.model.ValueObject.testPaper;
import com.example.demo.model.ValueObject.testQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.tools.*;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.lang.reflect.Parameter;
import java.util.List;
@CrossOrigin
@RestController
public class courseController {
    private final static String courseConnect="/course";//获取学生或教师所有可见课程
    private final static String getOneCourseUrl = "/admin/course/getOneCourse";//获取单个课程的详细信息
    private final static String AllCoursesUrl = "/admin/course/getAllCourses";//获取所有的课程
    private final static String addCourseUrl = "/admin/course/addCourse";//增加课程
    private final static String addCourseTeacher="/admin/course/addCourseTeacher";//增加课程与教师的关系
    private final static String updateCourseUrl="/admin/course/updateCourse";//更新课程
    private final static String updateCourseTeacherUrl ="/admin/course/updateCourseTeacher";//更新老师与课程的关系
    private final static String deleteCourseUrl="/admin/course/deleteCourse";//删除课程
    private final static String deleteCourseTeacherUrl="/admin/course/deleteCourseTeacher";//删除教师与课程的对应关系
    @Autowired
    private com.example.demo.dao.courseConnection courseConnection;
    @RequestMapping(value=getOneCourseUrl+"/{Cno}",produces = "application/json; charset=utf-8")
    public String getGetOneCourse(@PathVariable String Cno)
    {
        return JSON.toJSONString(courseOperation.getCourse(Cno));
    }

    @RequestMapping(value=courseConnect,produces = "application/json; charset=utf-8")
    public String GetStudentPage(HttpServletRequest request)
    {
        //装载学生可见的所有课程信息
        return JSON.toJSONString(courseConnection.getCourses(request.getSession().getAttribute("user")));
    }
    @Autowired
    private CourseOperation courseOperation;

    @RequestMapping(value=AllCoursesUrl,produces = "application/json; charset=utf-8")
    public String getAllCourses()
    {
        return JSON.toJSONString(courseOperation.getCourses());
    }
    @RequestMapping(value=addCourseUrl,produces = "application/json; charset=utf-8")
    public String addCourse(@RequestParam String name,
                            @RequestParam String profession,
                            @RequestParam String year_admission,
                            @RequestParam String ifopen)
    {
        String Cno = courseOperation.addCourse(name, profession, year_admission, ifopen).getCno();
        String result ="{\"Cno\":\"";
        result+=Cno;
        result+="\",\"state\":\"success\"}";
        return result;
    }
    @RequestMapping(value=addCourseTeacher,produces = "application/json; charset=utf-8")
    public String addCourseTeacher(@RequestParam String Cno,@RequestParam String TID)
    {
        courseOperation.addCourseTeacher(Cno, TID);
        return "{\"state\":\"success\"}";
    }
    @RequestMapping(value=updateCourseUrl,produces = "application/json; charset=utf-8")
    public String updateCourse(@RequestParam String Cno,
                               @RequestParam String name,
                               @RequestParam String profession,
                               @RequestParam String year_admission,
                               @RequestParam String ifopen)
    {
        courseOperation.updateCourse(Cno, name, profession, year_admission, ifopen);
        return "{\"state\":\"success\"}";
    }
    @RequestMapping(value=updateCourseTeacherUrl+"/{oldTID}",produces = "application/json; charset=utf-8")
    public String updateCourseTeacher(@RequestParam String Cno,@RequestParam String TID,@PathVariable String oldTID)
    {
        courseOperation.updateCourseTeacher(Cno, TID, oldTID);
        return "{\"state\":\"success\"}";
    }
    @RequestMapping(value=deleteCourseUrl+"/{Cno}",produces = "application/json; charset=utf-8")
    public String deleteCourse(@PathVariable String Cno)
    {
        courseOperation.deleteCourse(Cno);
        return "{\"state\":\"success\"}";
    }
    @RequestMapping(value=deleteCourseTeacherUrl,produces = "application/json; charset=utf-8")
    public String deleteCourseTeacher(@RequestParam String Cno,
                                      @RequestParam String TID)
    {
        courseOperation.deleteCourseTeacher(Cno, TID);
        return "{\"state\":\"success\"}";
    }
}
