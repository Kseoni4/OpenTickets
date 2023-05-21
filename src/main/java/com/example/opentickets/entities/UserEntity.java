package com.example.opentickets.entities;

import com.example.opentickets.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID userId;

    private String email;

    private LocalDate registerDate;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    /*
        SELECT *
          FROM users
          JOIN tickets ON
               tickets.user_id = users.user_id
     */

    @OneToMany
    @JoinColumn(name = "ticket_owner_user_id")
    private List<TicketEntity> ownedTickets;
}
