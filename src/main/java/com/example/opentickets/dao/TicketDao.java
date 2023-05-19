package com.example.opentickets.dao;

import com.example.opentickets.entities.TicketEntity;
import com.example.opentickets.messages.requests.TicketRegisterRequest;
import com.example.opentickets.models.TicketModel;
import com.example.opentickets.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TicketDao {

    private final TicketRepository ticketRepository;

    private final UserDao userDao;
    public TicketModel saveTicket(TicketRegisterRequest ticketRegisterRequest){
        TicketEntity savedTicket = ticketRepository.save(
                TicketEntity.builder()
                        .eventName(ticketRegisterRequest.getEventName())
                        .eventDate(ticketRegisterRequest.getEventDate())
                        .userId(userDao.getUserIdByEmail(ticketRegisterRequest.getEmailOwner()))
                        .isRedeemed(false)
                        .ticketType(ticketRegisterRequest.getTicketType())
                        .build()
        );

        return TicketModel.builder()
                .ticketId(savedTicket.getTicketId())
                .emailOwner(ticketRegisterRequest.getEmailOwner())
                .eventDate(savedTicket.getEventDate())
                .isRedeemed(savedTicket.isRedeemed())
                .ticketType(savedTicket.getTicketType())
                .eventName(savedTicket.getEventName())
                .build();
    }


    public List<TicketModel> getAllTickets(){
        ArrayList<TicketModel> list = new ArrayList<>();

        for(TicketEntity ticket : ticketRepository.findAll()){
            list.add(
                    TicketModel.builder()
                            .ticketId(ticket.getTicketId())
                            .emailOwner(userDao.getEmailById(ticket.getUserId()))
                            .eventDate(ticket.getEventDate())
                            .isRedeemed(ticket.isRedeemed())
                            .ticketType(ticket.getTicketType())
                            .eventName(ticket.getEventName())
                            .build()
            );
        }

        return list;
    }

}
