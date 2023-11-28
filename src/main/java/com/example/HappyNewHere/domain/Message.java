package com.example.HappyNewHere.domain;

import com.example.HappyNewHere.dto.MessageDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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

    // dto -> entity
    public Message(MessageDto messageDto) {
        this.messageId = messageDto.getMessageId();
        this.createdDate = messageDto.getCreatedDate();
        this.context = messageDto.getContext();
        this.sender = messageDto.getSender();
        this.receiver = messageDto.getReceiver();
        this.decorate = messageDto.getDecorate();
        this.isHidden = messageDto.isHidden();  // 왜 바로접근가능??
    }

    // patch로 변경 ..?
    public void update(MessageDto messageDto) {
        this.messageId = messageDto.getMessageId();
        this.createdDate = messageDto.getCreatedDate();
        this.context = messageDto.getContext();
        this.sender = messageDto.getSender();
        this.receiver = messageDto.getReceiver();
        this.decorate = messageDto.getDecorate();
        this.isHidden = messageDto.isHidden();
    }
}
