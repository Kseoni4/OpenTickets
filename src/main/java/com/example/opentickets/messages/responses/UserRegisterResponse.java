package com.example.opentickets.messages.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponse {

    private String email;

    private UUID uuid;

    private String token;

}
