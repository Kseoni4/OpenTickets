package com.example.opentickets.service;

import com.example.opentickets.messages.requests.TicketRegisterRequest;
import com.example.opentickets.messages.responses.TicketRegisterResponse;
import com.example.opentickets.models.TicketModel;

import java.util.List;

public interface TicketService {

    TicketModel register(TicketRegisterRequest ticketRegisterRequest);

    List<TicketModel> getAllTickets();
}
