package com.example.HappyNewHere.dto;

import com.example.HappyNewHere.domain.Messages;
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
    public MessageDto(Messages messages) {
        this.messageId = messages.getMessageId();
        this.createdDate = messages.getCreatedDate();
        this.context = messages.getContext();
        this.sender = messages.getSender();
        this.receiver = messages.getReceiver();
        this.decorate = messages.getDecorate();
        this.isHidden = messages.isHidden();
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