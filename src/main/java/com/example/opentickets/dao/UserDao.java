package com.example.opentickets.dao;

import com.example.opentickets.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;


    public UUID getUserIdByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow().getUserId();
    }

    public String getEmailById(UUID userId){
        return userRepository.findById(userId).orElseThrow().getEmail();
    }
}
