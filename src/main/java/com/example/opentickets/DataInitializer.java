package com.example.opentickets;

import com.example.opentickets.entities.UserEntity;
import com.example.opentickets.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer {


    private final UserRepository userRepository;


    @PostConstruct
    private void generate(){
        log.info("DataInitializer post construct");
        createUser();
    }

    private void createUser(){
        userRepository.save(
                UserEntity.builder()
                        .email("greatoldfag@gmail.com")
                        .registerDate(LocalDate.now()).build()
        );
    }
}
