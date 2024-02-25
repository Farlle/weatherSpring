package com.example.demo.configuration;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoDetailService implements UserDetailsService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = Optional.ofNullable(userInfoRepository.getByUsername(username));
        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserInfoUserDetails(userInfo.get());
    }
}
