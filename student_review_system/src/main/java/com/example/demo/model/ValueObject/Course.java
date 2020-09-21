package com.example.demo.model.ValueObject;

import lombok.*;

/**
 * 课程类
 */
@Data
@ToString
public class Course {
    private String Cno;//课程号
    private String name;//课程名
    private String semester;//开设学期
    private String profession;//开设专业
    private String year_admission;//学生入学时间（17级）
    private String TID;//上课教师
    private String ifopen;
    @Override
    public int hashCode()
    {
        return Cno.hashCode()+name.hashCode()+year_admission.hashCode();
    }
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Course)
        {
            return this.Cno.equals(((Course) o).Cno)&&this.name.equals(((Course) o).name)&&this.year_admission.equals(((Course) o).year_admission);
        }
        return false;
    }
}
