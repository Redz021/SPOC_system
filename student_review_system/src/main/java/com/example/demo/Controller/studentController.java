package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.StudentOperation;
import com.example.demo.dao.courseConnection;
import com.example.demo.model.Entity.CourseConnectEntity;
import com.example.demo.model.ValueObject.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
@CrossOrigin
@RestController
public class studentController {
    private final static String getAllStudentsUrl = "/admin/getAllStudents";//获取所有学生
    private final static String getAllStudentsWhereUrl = "/admin/getAllStudentsWhere";//根据专业以及入学时间获取学生
    private final static String addStudentUrl = "/admin/addStudent";//新增一个学生
    private final static String addStudentsUrl = "/admin/addStudents";//新增多个学生
    private final static String updateStudentUrl = "/admin/updateStudent";//更新一个学生
    private final static String deleteStudentUrl="/admin/deleteStudent";//删除一个学生
    private final static String allProfessionUrl = "/admin/allProfession";//所有已有的学生专业
    @Autowired
    private courseConnection courseConnection;//课程与教师和学生的联系

    @Autowired
    private StudentOperation studentOperation;
    @RequestMapping(value=getAllStudentsUrl,produces = "application/json; charset=utf-8")
    public String getAllStudents()
    {
        return JSON.toJSONString(studentOperation.getAllStudents());
    }
    @RequestMapping(value=getAllStudentsWhereUrl,produces = "application/json; charset=utf-8")
    public String getAllStudentsWhere(@RequestParam("profession")String profession,@RequestParam("year_admission")String year_admission)
    {
        return JSON.toJSONString(studentOperation.getStudentsWhere(profession, year_admission));
    }
    @RequestMapping(value=addStudentUrl,produces = "application/json; charset=utf-8")
    public String addStudent(@RequestParam("stuId")String stuId,
                             @RequestParam("stuName")String stuName,
                             @RequestParam("profession")String profession,
                             @RequestParam("year_admission")String year_admission,
                             @RequestParam("password")String password)
    {
        if(studentOperation.addStudent(stuId, stuName, profession, year_admission, password))
        {
            return "{\"state\":\"success\"}";
        }
        return "{\"state\":null}";
    }
    @PostMapping(value=addStudentsUrl,produces = "application/json; charset=utf-8")
    public String addStudents(@RequestBody JSONObject jsonParam)
    {
        List<LinkedHashMap<String,String>> students =  (List<LinkedHashMap<String,String>>)jsonParam.get("students");
        return studentOperation.addStudents(students);
    }
    @RequestMapping(value=updateStudentUrl+"/{oldStuId}",produces = "application/json; charset=utf-8")
    public String updateStudent(@RequestParam("stuId")String stuId,
                                @RequestParam("stuName")String stuName,
                                @RequestParam("profession")String profession,
                                @RequestParam("year_admission")String year_admission,
                                @PathVariable String oldStuId)
    {
        if(studentOperation.updateStudent(stuId, stuName, profession, year_admission,oldStuId))
        {
            return "{\"state\":\"success\"}";
        }
        return "{\"state\":null}";
    }
    @RequestMapping(value=deleteStudentUrl,produces = "application/json; charset=utf-8")
    public String deleteStudent(@RequestParam("stuId")String stuId)
    {
        studentOperation.deleteStudent(stuId);
        return "{\"state\":\"success\"}";
    }
    @RequestMapping(value=allProfessionUrl,produces = "application/json; charset=utf-8")
    public String getAllProfession()
    {
        return JSON.toJSONString(studentOperation.getAllProfession());
    }
}
