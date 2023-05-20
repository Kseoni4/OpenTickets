package com.example.opentickets.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenModel {

    private String email;

    private String token;

    private UUID userId;

}
