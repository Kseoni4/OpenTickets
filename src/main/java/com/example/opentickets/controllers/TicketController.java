package com.example.opentickets.controllers;

import com.example.opentickets.messages.requests.TicketRedeemRequest;
import com.example.opentickets.messages.requests.TicketRegisterRequest;
import com.example.opentickets.messages.responses.AllTicketsResponse;
import com.example.opentickets.messages.responses.TicketRegisterResponse;
import com.example.opentickets.models.TicketModel;
import com.example.opentickets.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    //http://localhost:8080/tickets/register
    @PostMapping("/register")
    TicketRegisterResponse ticketRegister(@RequestBody TicketRegisterRequest ticketRegisterRequest){
        log.info("ticket register request {}", ticketRegisterRequest);
        TicketModel ticketModel = ticketService.register(ticketRegisterRequest);
        return TicketRegisterResponse.builder()
                .ticketId(ticketModel.getTicketId().toString())
                .eventName(ticketModel.getEventName())
                .eventDate(ticketModel.getEventDate())
                .ownerEmail(ticketModel.getEmailOwner())
                .build();
    }

    //http://localhost:8080/tickets/redeem
    @PostMapping("/redeem")
    TicketModel redeemTicket(@RequestBody TicketRedeemRequest ticketRedeemRequest){
       return ticketService.redeemTicket(ticketRedeemRequest);
    }

    //http://localhost:8080/tickets/
    @GetMapping("/")
    AllTicketsResponse getAllTickets(){
        return new AllTicketsResponse(ticketService.getAllTickets());
    }
}
