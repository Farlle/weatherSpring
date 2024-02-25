package com.example.demo.configuration;

import com.example.demo.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Component
@Setter
@Getter
public class UserInfoUserDetails implements UserDetails {
    @Autowired
    private UserInfo userInfo;

    private String name;
    private String password;
    private List<GrantedAuthority> authorities;

   /* public UserInfoUserDetails(UserInfo userInfo) {
        this.userInfo = userInfo;
    }*/

    public UserInfoUserDetails(UserInfo userInfo){
       // this.userInfo = userInfo;
        name = userInfo.getName();
        password = userInfo.getPassword();
//        authorities = Arrays.stream(userInfo.getRole().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getName();
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
