package com.example.opentickets.repositories;

import com.example.opentickets.entities.TicketEntity;
import com.example.opentickets.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {

    List<TicketEntity> findAllByTicketOwner(UserEntity ticketOwner);

    //Аннотация Query позволяет определить запрос вручную
    @Query("""
        update  TicketEntity tk
        set     tk.isRedeemed = true
        where   tk.ticketId = :ticketId
    """)
    @Modifying //требуется для UPDATE/DELETE запросов
    void redeemTicket(UUID ticketId);
}
