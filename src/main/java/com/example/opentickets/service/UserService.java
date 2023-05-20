package com.example.opentickets.service;

import com.example.opentickets.messages.requests.UserLoginRequest;
import com.example.opentickets.messages.requests.UserRegisterRequest;
import com.example.opentickets.models.TokenModel;

public interface UserService {

    TokenModel register(UserRegisterRequest registerRequest);

    TokenModel login(UserLoginRequest loginRequest);

}
