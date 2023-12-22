package com.example.HappyNewHere.dto.request;

public record AccountRequestDto(
        String userId,
        String nickname,
        String profileImg,
        String stateMsg
) {
    public AccountRequestDto(String userId, String nickname, String profileImg) {
        this(userId, nickname, profileImg, null);
    }
}