package com.example.opentickets.jwt;

import com.example.opentickets.models.UserModel;

public interface JwtService {

    String generateToken(UserModel userModel);

    UserModel parseToken(String jwt);


}
