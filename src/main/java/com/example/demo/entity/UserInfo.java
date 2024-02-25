package com.example.demo.entity;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfo {
    private int id;
    private String name;
    private String login;
    private String password;
    private String role;


    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }
}
