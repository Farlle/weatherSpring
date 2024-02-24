package com.example.demo.repository;

import com.example.demo.entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserInfoRepository {

    private List<UserInfo> userInfos = new ArrayList<>();
    private int id = 0;

    {
        userInfos.add(new UserInfo(id++,"Sashok", "gav@", "123","Kryt"));
        userInfos.add(new UserInfo(id++,"Dimas", "mya@", "123","Kryt"));
        userInfos.add(new UserInfo(id++,"Oleg", "you@", "123","Kryt"));

    }


    public void createUserInfo(UserInfo employee) {
        userInfos.add(employee);

    }

    public UserInfo getById(){
        return userInfos.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Employee with id " + id + " not found"));
    }


    public UserInfo getByUsername(String username) {
        return userInfos.stream()
                .filter(x -> x.getName() == username)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Employee with id " + username + " not found"));

    }
}
