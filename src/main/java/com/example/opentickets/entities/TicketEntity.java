package com.example.opentickets.entities;

import com.example.opentickets.models.enums.TicketType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TicketEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID ticketId;

    private String eventName;

    private LocalDate eventDate;

    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    private boolean isRedeemed;

    private UUID userId;

}
