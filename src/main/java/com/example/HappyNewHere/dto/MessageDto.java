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

    public static MessageDto from(Messages messages,String sender,String receiver) {
        return new MessageDto(
                messages.getMessageId(),
                messages.getCreatedDate(),
                messages.getContext(),
                sender,
                receiver,
                messages.getPaperNum(),
                messages.isAnonymous()
        );
    }
}