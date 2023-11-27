package com.example.HappyNewHere.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Account {
    @Id
    @Setter @Column(nullable = false) private Long accountId;
    @Setter private String nickname;
    @Setter private String userId;
    @Setter private String profileImg;


    public Account(Long accountId, String nickname, String userId, String profileImg) {
        this.accountId = accountId;
        this.nickname = nickname;
        this.userId = userId;
        this.profileImg = profileImg;
    }

    public Account(Long accountId) {
        this.accountId = accountId;
    }
}