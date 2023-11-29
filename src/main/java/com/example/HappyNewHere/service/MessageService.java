package com.example.HappyNewHere.service;

import com.example.HappyNewHere.domain.Message;
import com.example.HappyNewHere.dto.MessageDto;
import com.example.HappyNewHere.repository.MessageRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    // 메세지 생성 (Create)
    public MessageDto createMessage(MessageDto messageDto) {
        Message message = new Message(messageDto);
        messageRepository.save(message);
        return new MessageDto(message);
    }

    // 내가 받은 메세지들 가져오기 (Read)
    public List<MessageDto> findAllMessages(String receiver) {
        try{
            List<Message> messageList = messageRepository.findByReceiver(receiver);
            List<MessageDto> messageDtoList = new ArrayList<>();
            for (Message message : messageList) {
                messageDtoList.add(new MessageDto(message));
            }
            return messageDtoList;
        } catch (Exception e) {
//            throw new DBEmptyDataException("조회 실패!");
        }
        return null;
    }

    // 메세지 하나 가져오기 (Read)
    public MessageDto findOneMessage(Long messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(
                () -> new IllegalArgumentException("조회 실패")
                // ....? 람다함수 공부좀
        );
        return new MessageDto(message);
    }

    // 메세지 수정 (Update)
    @Transactional
    public Long updateMessage(MessageDto messageDto) {
        Message message = messageRepository.findById(messageDto.getMessageId()).orElseThrow(
                () -> new IllegalArgumentException("해당 메세지가 존재하지 않습니다.")
        );
        message.update(messageDto);
        return message.getMessageId();
    }

    // 메세지 삭제 (Delete)
    @Transactional
    public Long deleteMessage(Long messageId) {
        messageRepository.deleteById(messageId);
        return messageId;
    }
}