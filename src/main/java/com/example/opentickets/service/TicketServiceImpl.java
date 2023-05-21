package com.example.opentickets.service;

import com.example.opentickets.dao.TicketDao;
import com.example.opentickets.dao.UserDao;
import com.example.opentickets.entities.TicketEntity;
import com.example.opentickets.messages.requests.TicketRedeemRequest;
import com.example.opentickets.messages.requests.TicketRegisterRequest;
import com.example.opentickets.models.TicketModel;
import com.example.opentickets.repositories.TicketRepository;
import com.example.opentickets.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketDao ticketDao;

    @Override
    public TicketModel register(TicketRegisterRequest ticketRegisterRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String email = (String) auth.getPrincipal();
        ticketRegisterRequest.setEmailOwner(email);
        return ticketDao.saveTicket(ticketRegisterRequest);
    }

    @Override
    public List<TicketModel> getAllTickets(){
        return ticketDao.getAllTickets();
    }

    @Override
    public TicketModel redeemTicket(TicketRedeemRequest ticketRedeemRequest) {
        if(!ticketDao.isTicketExists(ticketRedeemRequest.getTicketId())){
            throw new RuntimeException();
        }

        TicketEntity ticketEntity = ticketDao.redeemTicketById(ticketRedeemRequest.getTicketId());

        return TicketModel.fromEntity(ticketEntity);
    }

}
