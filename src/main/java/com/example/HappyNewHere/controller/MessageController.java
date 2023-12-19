package com.example.HappyNewHere.controller;

import com.example.HappyNewHere.dto.MessageDto;
import com.example.HappyNewHere.service.MessageService;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/message")
@CrossOrigin
public class MessageController {
    private final MessageService messageService;

    // 권한들 넣어주셈요
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/create")
    public MessageDto createMessage(@RequestBody MessageDto messageDto) {
        return messageService.createMessage(messageDto);
    }

    @GetMapping("/read/{userId}")
    public List<MessageDto> findAllMessages(@PathVariable String userId) {
        return messageService.findAllMessages(userId);
    }

    @GetMapping("/read/{userId}/{messageId}")
    public MessageDto findMessage(@PathVariable String userId, @PathVariable Long messageId) {
        return messageService.findOneMessage(messageId);
    }

    @PutMapping("/update")
    public Long updateMessage(@RequestBody MessageDto messageDto) {
        return messageService.updateMessage(messageDto);
    }

    @DeleteMapping("/delete/{messageId}")
    public Long deleteMessage(@PathVariable Long messageId) {
        return messageService.deleteMessage(messageId);
    }
}
