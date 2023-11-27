package com.example.HappyNewHere.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Calender {
    @Id
    private Long accountId;
    private int color;

    @MapsId
    @OneToOne
    @JoinColumn(name = "accountId")
    private Account account;
}
