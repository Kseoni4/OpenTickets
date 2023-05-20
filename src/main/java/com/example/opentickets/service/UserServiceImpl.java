package com.example.opentickets.service;

import com.example.opentickets.dao.UserDao;
import com.example.opentickets.entities.UserEntity;
import com.example.opentickets.jwt.JwtService;
import com.example.opentickets.messages.requests.UserLoginRequest;
import com.example.opentickets.messages.requests.UserRegisterRequest;
import com.example.opentickets.models.TokenModel;
import com.example.opentickets.models.UserModel;
import com.example.opentickets.models.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public TokenModel register(UserRegisterRequest registerRequest) {

        UserEntity newUser = userDao.createUser(
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                UserRole.USER
        );


        String jwt = jwtService.generateToken(UserModel.fromEntity(newUser));

        return new TokenModel(
                newUser.getEmail(),
                jwt,
                newUser.getUserId()
        );
    }

    @Override
    public TokenModel login(UserLoginRequest loginRequest) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
