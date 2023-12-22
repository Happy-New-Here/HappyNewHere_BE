package com.example.HappyNewHere.dto;

import com.example.HappyNewHere.domain.Calender;
import com.example.HappyNewHere.domain.Messages;
import com.example.HappyNewHere.dto.response.MsgResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class CalenderDto {
    private boolean isOwner;
    private Calender calender;
    private List<MsgResponseDto> messagesList;
}
