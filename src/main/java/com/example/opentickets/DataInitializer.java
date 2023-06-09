package com.example.opentickets;

import com.example.opentickets.entities.TicketEntity;
import com.example.opentickets.entities.UserEntity;
import com.example.opentickets.models.UserModel;
import com.example.opentickets.models.enums.Permission;
import com.example.opentickets.models.enums.TicketType;
import com.example.opentickets.models.enums.UserRole;
import com.example.opentickets.repositories.TicketRepository;
import com.example.opentickets.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer {


    private final UserRepository userRepository;

    private final TicketRepository ticketRepository;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void generate(){
        log.info("DataInitializer post construct");
        createUser();
        createTicket();
    }

    private void createUser(){
        if(userRepository.findByEmail("greatoldfag@gmail.com").isPresent()){
            return;
        }

        userRepository.save(
                UserEntity.builder()
                        .email("greatoldfag@gmail.com")
                        .password(passwordEncoder.encode("password"))
                        .userRole(UserRole.USER)
                        .registerDate(LocalDate.now()).build()
        );

        UserEntity user = UserEntity.builder()
                .email("greatoldfag2@gmail.com")
                .password(passwordEncoder.encode("password"))
                .userRole(UserRole.USER)
                .registerDate(LocalDate.now()).build();

        UserModel.fromEntity(user).getAuthoritySet().add(new SimpleGrantedAuthority(Permission.USER_WRITE.getPermission()));

        userRepository.save(user);



        userRepository.save(
                UserEntity.builder()
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("password"))
                        .userRole(UserRole.ADMIN)
                        .registerDate(LocalDate.now()).build()
        );
    }

    private void createTicket(){
        ticketRepository.save(
                TicketEntity.builder()
                        .eventName("TestEvent")
                        .eventDate(LocalDate.now().plusDays(new Random().nextInt(365)+1))
                        .ticketType(TicketType.values()[new Random().nextInt(4)])
                        .ticketOwner(userRepository.findByEmail("greatoldfag@gmail.com").get())
                        .isRedeemed(false).build()
        );
    }
}
