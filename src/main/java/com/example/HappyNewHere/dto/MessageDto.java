package com.example.HappyNewHere.dto;

import com.example.HappyNewHere.domain.Messages;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDto {
    private Long messageId;
    private LocalDateTime createdDate;
    private String context;
    private String sender;
    private String receiver;
    private int paperNum;
    private boolean anonymous;

    // entity -> dto
//    public MessageDto(Messages messages) {
//        this.messageId = messages.getMessageId();
//        this.createdDate = messages.getCreatedDate();
//        this.context = messages.getContext();
//        this.sender = messages.getSender();
//        this.receiver = messages.getReceiver();
//        this.paperNum = messages.getPaperNum();
//        this.anonymous = messages.isAnonymous();
//    }

    public MessageDto(Long messageId, LocalDateTime createdDate, String context, String sender, String receiver,
                      int paperNum, boolean anonymous) {
        this.messageId = messageId;
        this.createdDate = createdDate;
        this.context = context;
        this.sender = sender;
        this.receiver = receiver;
        this.paperNum = paperNum;
        this.anonymous = anonymous;
    }
}