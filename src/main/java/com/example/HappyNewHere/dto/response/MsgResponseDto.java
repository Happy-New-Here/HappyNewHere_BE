package com.example.HappyNewHere.dto.response;

import com.example.HappyNewHere.domain.Messages;
import com.example.HappyNewHere.dto.MessageDto;

import java.time.LocalDateTime;

public record MsgResponseDto(
        String context,
        String senderId,
        String senderNickname,
        int paperNum,
        boolean anonymous,
        LocalDateTime day
) {
    public static MsgResponseDto of(Messages messages,String senderUserId, String senderNickname){
        return new MsgResponseDto(
                messages.getContext(),
                senderUserId,
                senderNickname,
                messages.getPaperNum(),
                messages.isAnonymous(),
                messages.getCreatedDate()
        );
    }
}
