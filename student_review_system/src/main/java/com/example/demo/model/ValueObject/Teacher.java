package com.example.demo.model.ValueObject;

import lombok.*;

/**
 * 教师类
 */

@Data
@ToString
public class Teacher {
    private String TID;//职工号
    private String teacherName;//教师姓名
    private String department;//所在院系
    @Override
    public int hashCode()
    {
        return TID.hashCode();
    }
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Teacher)
        {
            return this.TID.equals( ((Teacher) o).TID);
        }
        return false;
    }
}
