package com.example.opentickets.messages.responses;

import com.example.opentickets.models.TicketModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
public class AllTicketsResponse {

    private List<TicketModel> tickets;

}
