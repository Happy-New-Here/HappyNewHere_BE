package com.example.HappyNewHere.controller;

import com.example.HappyNewHere.dto.MessageDto;
import com.example.HappyNewHere.service.MessageService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/message")
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
