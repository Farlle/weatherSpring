package com.example.demo.repository;

import com.example.demo.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Getter
@Setter
public class UserInfoRepository {

    private HashMap<Integer, UserInfo> userInfoMap = new HashMap<>();
    private int id = 0;

    {
        userInfoMap.put(id++, new UserInfo(id++, "Sashok", "gav@", "123", "Kryt"));
        userInfoMap.put(id++, new UserInfo(id++, "Dimas", "mya@", "123", "Kryt"));
        userInfoMap.put(id++, new UserInfo(id++, "Oleg", "you@", "123", "Kryt"));

    }

    public void createUserInfo(UserInfo user) {
        userInfoMap.put(id++, user);
    }
    
    public UserInfo getByUsername(String name) {
        return userInfoMap.values().stream()
                .filter(userInfo -> userInfo.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("user with id " + name + " not found"));

    }
}
