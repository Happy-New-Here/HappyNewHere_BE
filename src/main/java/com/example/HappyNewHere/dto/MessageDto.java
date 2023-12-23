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
    private Long sender; // kakaoI
    private Long receiver; //
    private int paperNum;
    private boolean anonymous;

    public MessageDto(Long messageId, LocalDateTime createdDate, String context, Long sender, Long receiver,
                      int paperNum, boolean anonymous) {
        this.messageId = messageId;
        this.createdDate = createdDate;
        this.context = context;
        this.sender = sender;
        this.receiver = receiver;
        this.paperNum = paperNum;
        this.anonymous = anonymous;
    }

    public static MessageDto from(Messages messages) {
        return new MessageDto(messages.getMessageId(), messages.getCreatedDate(), messages.getContext(), messages.getSender(), messages.getReceiver(), messages.getPaperNum(), messages.isAnonymous());
    }
}