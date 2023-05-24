package com.example.opentickets.controllers;

import com.example.opentickets.dao.TicketDao;
import com.example.opentickets.messages.requests.TicketRegisterRequest;
import com.example.opentickets.messages.responses.TicketRegisterResponse;
import com.example.opentickets.models.enums.TicketType;
import com.example.opentickets.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.Random;

@SpringBootTest
@Slf4j
public class TicketControllerTest {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketController ticketController;

    @BeforeAll
    static void loadContext(){
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "test@mail.ru", null, null
                )
        );
    }

    @Test
    void ticketIsRegister(){
        TicketRegisterRequest registerRequest = TicketRegisterRequest.builder()
                .eventDate(LocalDate.now().plusDays(new Random().nextInt(365)+1))
                .ticketType(TicketType.values()[new Random().nextInt(4)])
                .eventName("TicketTestEvent")
                .emailOwner("test@test")
                .build();

        TicketRegisterResponse response = ticketController.ticketRegister(registerRequest);

        Assertions.assertNotNull(response);

        Assertions.assertNotNull(response.getTicketId());

        Assertions.assertEquals(registerRequest.getEventDate(), response.getEventDate());
    }

}
