package com.example.HappyNewHere.controller;

import com.example.HappyNewHere.dto.MessageDto;
import com.example.HappyNewHere.dto.request.MessageRequestDto;
import com.example.HappyNewHere.service.MessageService;
import com.example.HappyNewHere.utils.AuthenticateUtils;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/message")
@CrossOrigin
public class MessageController {
    private final MessageService messageService;
    private final AuthenticateUtils authenticateUtils;

    // 권한들 넣어주셈요
    public MessageController(MessageService messageService, AuthenticateUtils authenticateUtils) {
        this.messageService = messageService;
        this.authenticateUtils = authenticateUtils;
    }

    @PostMapping("/create")
    public MessageDto createMessage(
            Authentication authentication,
            @RequestBody MessageRequestDto messageRequestDto)
    {
        String userId = authenticateUtils.getUserId(authentication);
        return messageService.createMessage(userId,messageRequestDto);
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
