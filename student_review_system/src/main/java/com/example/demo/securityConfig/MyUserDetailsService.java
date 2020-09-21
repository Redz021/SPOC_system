package com.example.demo.securityConfig;

import com.example.demo.model.ValueObject.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private com.example.demo.model.service.userService userService;

    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException
    {
        UserLogin userLogin = userService.getOneUser(username);
        if(userLogin==null)
        {
            throw new UsernameNotFoundException("用户不存在");
        }
        userLogin.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(userLogin.getAuthority()));
        return userLogin;
    }


}
