package com.example.opentickets.messages.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketRegisterResponse {

    private String eventName;

    private String ticketId;

    private String ownerEmail;

    private LocalDate eventDate;

}
