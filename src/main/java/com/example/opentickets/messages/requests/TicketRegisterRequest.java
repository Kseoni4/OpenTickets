package com.example.opentickets.messages.requests;


import com.example.opentickets.models.enums.TicketType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class TicketRegisterRequest {

    private String eventName;

    private LocalDate eventDate;

    private TicketType ticketType;

    private String emailOwner;

}
