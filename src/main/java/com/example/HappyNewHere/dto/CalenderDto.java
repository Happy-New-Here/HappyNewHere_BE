package com.example.HappyNewHere.dto;

import com.example.HappyNewHere.domain.Calender;
import com.example.HappyNewHere.domain.Message;
import lombok.Data;

import java.util.List;

@Data
public class CalenderDto {
    private boolean isOwner;
    private Calender calender;
    private List<Message> messageList;
}