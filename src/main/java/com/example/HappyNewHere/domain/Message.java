package com.example.HappyNewHere.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private LocalDateTime createdDate;
    private String context;
    private String sender;
    private String receiver;
    private int decorate;
    private boolean isHidden;
}
