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
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private LocalDateTime createdDate;
    private String context;
    private Long sender; // kakooId
    private Long receiver; //kakaoid
    private int paperNum;
    private boolean anonymous;


    private Messages() {
    }
    private Messages(LocalDateTime createdDate, String context, Long sender, Long receiver, int paperNum, boolean anonymous) {
        this.createdDate = createdDate;
        this.context = context;
        this.sender = sender;
        this.receiver = receiver;
        this.paperNum = paperNum;
        this.anonymous = anonymous;
    }

    public static Messages of(LocalDateTime createdDate, String context, Long sender, Long receiver, int paperNum, boolean anonymous) {
        return new Messages( createdDate, context, sender, receiver, paperNum, anonymous);
    }

}
