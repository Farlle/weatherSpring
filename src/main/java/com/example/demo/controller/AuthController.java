package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserInfoRepository userInfoRepository;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private AuthRequest authRequest;

    public AuthController(UserInfoRepository userInfoRepository, JwtService jwtService,
                          AuthenticationManager authenticationManager) {
        this.userInfoRepository = userInfoRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/new")
    public Map<String, String> createUser(@RequestBody UserInfo userInfo) {
        String rawPassword = userInfo.getPassword();

        String encodedPassword = passwordEncoder.encode(rawPassword);
        userInfo.setPassword(encodedPassword);

        userInfoRepository.createUserInfo(userInfo);
        String token = jwtService.generateToken(userInfo.getLogin());
        return Map.of("token", token);
    }

    @PostMapping("/authenticate")
    public Map<String, String> authenticateUser(@RequestBody AuthRequest authRequest) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authRequest.getName(),
                        authRequest.getPassword());
        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            System.out.println("Неверные учетные данные" + e.getMessage());
        } catch (LockedException e) {
            System.out.println("Пользователь заблокирован" + e.getMessage());
        } catch (DisabledException e) {
            System.out.println("Пользователь отключен" + e.getMessage());
        } catch (AccountExpiredException e) {
            System.out.println("Срок действия учетной записи истек" + e.getMessage());
        } catch (CredentialsExpiredException e) {
            System.out.println("Срок действия учетных данных истек" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Внутренняя ошибка сервера в контроллере " + e.getMessage());
        }
        String token = jwtService.generateToken(authRequest.getName());
        return Map.of("token", token);
    }

}
