package com.example.HappyNewHere.dto;

import lombok.Data;

@Data
public class TokenInfo {
    private String token_type;
    private String access_token;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int refresh_token_expires_in;
}