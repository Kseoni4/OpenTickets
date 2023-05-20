package com.example.opentickets.entities;

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

    /*
        SELECT *
          FROM users
          JOIN tickets ON
               tickets.user_id = users.user_id
     */

    @OneToMany
    @JoinColumn(name = "userId")
    private List<TicketEntity> ownedTickets;
}
