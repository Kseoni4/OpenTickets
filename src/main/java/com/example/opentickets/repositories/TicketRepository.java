package com.example.opentickets.repositories;

import com.example.opentickets.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {

    List<TicketEntity> findAllByUserId(UUID userId);

}
