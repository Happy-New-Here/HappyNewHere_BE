package com.example.HappyNewHere.dto;

import com.example.HappyNewHere.domain.Account;

public record AccountDto(
        Long accountId,
        String userId,
        String nickname,
        String profileImg
) {
    public static AccountDto of(Long accountId, String userId, String nickname, String profileImg) {
        return new AccountDto(accountId, userId, nickname, profileImg);
    }

    public static AccountDto from(Account account) {
        return new AccountDto(account.getAccountId(), account.getUserId(), account.getNickname(), account.getProfileImg());
    }

    public Account toEntity() {
        return new Account(accountId, nickname, userId, profileImg);
    }

}
