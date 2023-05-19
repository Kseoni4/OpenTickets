package com.example.opentickets.models;


import com.example.opentickets.models.enums.TicketType;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TicketModel {

    private final UUID ticketId;

    private String eventName;

    private LocalDate eventDate;

    private TicketType ticketType;

    private String emailOwner;

    private boolean isRedeemed;
}
