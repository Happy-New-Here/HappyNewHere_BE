package com.example.HappyNewHere.dto;

import com.example.HappyNewHere.domain.Account;
import lombok.NoArgsConstructor;

public record AccountDto(
        Long accountId,
        String userId,
        String nickname,
        String profileImg,
        String stateMsg
) {
    public static AccountDto of(Long accountId, String userId, String nickname, String profileImg) {
        return new AccountDto(accountId, userId, nickname, profileImg, null);
    }
    public static AccountDto of(Long accountId, String userId, String nickname, String profileImg, String stateMsg) {
        return new AccountDto(accountId, userId, nickname, profileImg, stateMsg);
    }

    public static AccountDto from(Account account) {
        return new AccountDto(account.getAccountId(), account.getUserId(), account.getNickname(), account.getProfileImg(), account.getStateMsg());
    }

    public Account toEntity() {
        return new Account(accountId, nickname, userId, profileImg, null);
    }

}
