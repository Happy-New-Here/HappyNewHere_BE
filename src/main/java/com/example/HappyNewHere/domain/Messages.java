package com.example.HappyNewHere.domain;

import com.example.HappyNewHere.dto.MessageDto;
import com.example.HappyNewHere.dto.request.AccountRequestDto;
import com.example.HappyNewHere.dto.request.MessageRequestDto;
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
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private LocalDateTime createdDate;
    private String context;
    private Long sender; // SenderUserId
    private Long receiver; // ReciverUserId
    private int paperNum;
    private boolean anonymous;

    // dto -> entity
//    public Messages(MessageDto messageDto) {
//        this.messageId = messageDto.getMessageId();
//        this.createdDate = messageDto.getCreatedDate();
//        this.context = messageDto.getContext();
//        this.sender = messageDto.getSender();
//        this.receiver = messageDto.getReceiver();
//        this.paperNum = messageDto.getPaperNum();
//        this.anonymous = messageDto.isAnonymous();  // 왜 바로접근가능??
//    }


    // patch로 변경 ..?
//    public void update(MessageDto messageDto) {
//        this.messageId = messageDto.getMessageId();
//        this.createdDate = messageDto.getCreatedDate();
//        this.context = messageDto.getContext();
//        this.sender = messageDto.getSender();
//        this.receiver = messageDto.getReceiver();
//        this.paperNum = messageDto.getPaperNum();
//        this.anonymous = messageDto.isAnonymous();
//    }
}
