package com.example.demo.configuration;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserInfoDetailService implements UserDetailsService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userInfo = Optional.ofNullable((userInfoRepository.getByUsername(username)));
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow();

    }
}
