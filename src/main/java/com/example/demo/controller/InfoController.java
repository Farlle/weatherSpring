package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class InfoController {

    @GetMapping("/hello")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String sayHello() {
        return "{\"message\": \"Hello User!\"}";
    }

}
