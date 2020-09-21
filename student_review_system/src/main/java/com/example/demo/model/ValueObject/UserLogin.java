package com.example.demo.model.ValueObject;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@ToString
public class UserLogin implements UserDetails {
    private String id;//教师的职工号或者学生的学号或者为管理员id
    private String password;//密码
    private String authority;//教师或者学生或管理员
    private List<GrantedAuthority> authorities;//权限

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
