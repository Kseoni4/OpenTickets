package com.example.opentickets.controllers;

import com.example.opentickets.messages.requests.UserLoginRequest;
import com.example.opentickets.messages.requests.UserRegisterRequest;
import com.example.opentickets.messages.responses.UserLoginResponse;
import com.example.opentickets.messages.responses.UserRegisterResponse;
import com.example.opentickets.models.TokenModel;
import com.example.opentickets.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //http://localhost:8080/auth/register
    @PostMapping("/register")
    public UserRegisterResponse register(@RequestBody UserRegisterRequest registerRequest){
        TokenModel tokenModel = userService.register(registerRequest);
        return new UserRegisterResponse(
                tokenModel.getEmail(),
                tokenModel.getUserId(),
                tokenModel.getToken()
        );
    }

    //http://localhost:8080/auth/login
    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest loginRequest){
        log.info(loginRequest.toString());

        TokenModel tokenModel = userService.login(loginRequest);

        return new UserLoginResponse(tokenModel.getEmail(), tokenModel.getToken());
    }

}
