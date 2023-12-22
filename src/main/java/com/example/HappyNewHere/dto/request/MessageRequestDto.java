package com.example.HappyNewHere.dto.request;

import com.example.HappyNewHere.domain.Messages;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MessageRequestDto {
    private String context;
    private String receiver;
    private int paperNum;
    private boolean anonymous;

    // entity -> dto
    public MessageRequestDto(Messages messages) {
        this.context = messages.getContext();
        this.receiver = messages.getReceiver();
        this.paperNum = messages.getPaperNum();
        this.anonymous = messages.isAnonymous();
    }

    public MessageRequestDto(String context, String receiver,
                      int paperNum, boolean anonymous) {
        this.context = context;
        this.receiver = receiver;
        this.paperNum = paperNum;
        this.anonymous = anonymous;
    }
}
