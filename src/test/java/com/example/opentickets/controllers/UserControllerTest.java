package com.example.opentickets.controllers;

import com.example.opentickets.dao.UserDao;
import com.example.opentickets.messages.requests.UserLoginRequest;
import com.example.opentickets.messages.responses.UserLoginResponse;
import com.example.opentickets.models.enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Slf4j
public
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void userLoginIsSuccess(){
       if(!userDao.isUserExist("test@mail.ru")) {

           userDao.createUser("test@mail.ru",
                   passwordEncoder.encode("password"),
                   UserRole.USER);
       }


        UserLoginRequest userLoginRequest = new UserLoginRequest("test@mail.ru", "password");
        UserLoginResponse userLoginResponse = userController.login(userLoginRequest);

        log.info("Login response: {}", userLoginResponse);

        Assertions.assertNotNull(userLoginResponse);

        Assertions.assertNotNull(userLoginResponse.getToken());
    }


}
