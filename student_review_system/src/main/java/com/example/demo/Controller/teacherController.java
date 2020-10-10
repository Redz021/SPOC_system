package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.dao.TeacherOperation;
import com.example.demo.dao.courseConnection;
import com.example.demo.model.Entity.CourseConnectEntity;
import com.example.demo.model.ValueObject.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@CrossOrigin
@RestController
public class teacherController {
    private final static String AllTeachersUrl="/admin/allTeachers";//查询所有教师
    private final static String addTeacherUrl="/admin/addTeacher";//新增一个老师
    private final static String updateTeacherUrl="/admin/updateTeacher";//更新一个老师
    private final static String deleteTeacherUrl="/admin/deleteTeacher";//删除一个老师


    @Autowired
    private TeacherOperation teacherOperation;
    @RequestMapping(value=AllTeachersUrl,produces = "application/json; charset=utf-8")
    public String AllTeachers()
    {
        return JSON.toJSONString(teacherOperation.getAllTeachers());
    }
    @RequestMapping(value=addTeacherUrl,produces = "application/json; charset=utf-8")
    public String addTeacher(@RequestParam("TID")String TID,
                             @RequestParam("name")String name,
                             @RequestParam("department")String department,
                             @RequestParam("password")String password)
    {
        if(teacherOperation.addTeacher(TID, name, department, password))
        {
            return "{\"state\":\"success\"}";
        }
        return "{\"state\":null}";
    }
    @RequestMapping(value=updateTeacherUrl+"/{oldTID}",produces = "application/json; charset=utf-8")
    public String updateTeacher(@RequestParam("TID")String TID,
                                @RequestParam("name")String name,
                                @RequestParam("department")String department,
                                @PathVariable String oldTID)
    {
        if(teacherOperation.updateTeacher(TID, name, department,oldTID))
        {
            return "{\"state\":\"success\"}";
        }
        return "{\"state\":null}";
    }
    @RequestMapping(value=deleteTeacherUrl+"/{TID}",produces = "application/json; charset=utf-8")
    public String deleteTeacher(@PathVariable String TID)
    {
        teacherOperation.deleteTeacher(TID);
        return "{\"state\":\"success\"}";
    }
}
