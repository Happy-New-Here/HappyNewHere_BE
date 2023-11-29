package com.example.HappyNewHere.dto;

import com.example.HappyNewHere.domain.Message;
import java.time.LocalDateTime;
import lombok.Data;
@Data
public class MessageDto {
    private Long messageId;
    private LocalDateTime createdDate;
    private String context;
    private String sender;
    private String receiver;
    private int decorate;
    private boolean isHidden;

    // entity -> dto
    public MessageDto(Message message) {
        this.messageId = message.getMessageId();
        this.createdDate = message.getCreatedDate();
        this.context = message.getContext();
        this.sender = message.getSender();
        this.receiver = message.getReceiver();
        this.decorate = message.getDecorate();
        this.isHidden = message.isHidden();
    }

    public MessageDto(Long messageId, LocalDateTime createdDate, String context, String sender, String receiver,
                      int decorate, boolean isHidden) {
        this.messageId = messageId;
        this.createdDate = createdDate;
        this.context = context;
        this.sender = sender;
        this.receiver = receiver;
        this.decorate = decorate;
        this.isHidden = isHidden;
    }
}