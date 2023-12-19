package com.example.HappyNewHere.service;

import com.example.HappyNewHere.domain.Messages;
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
        Messages messages = new Messages(messageDto);
        messageRepository.save(messages);
        return new MessageDto(messages);
    }

    // 내가 받은 메세지들 가져오기 (Read)
    public List<MessageDto> findAllMessages(String receiver) {
        try{
            List<Messages> messagesList = messageRepository.findByReceiver(receiver);
            List<MessageDto> messageDtoList = new ArrayList<>();
            for (Messages messages : messagesList) {
                messageDtoList.add(new MessageDto(messages));
            }
            return messageDtoList;
        } catch (Exception e) {
//            throw new DBEmptyDataException("조회 실패!");
        }
        return null;
    }

    // 메세지 하나 가져오기 (Read)
    public MessageDto findOneMessage(Long messageId) {
        Messages messages = messageRepository.findById(messageId).orElseThrow(
                () -> new IllegalArgumentException("조회 실패")
                // ....? 람다함수 공부좀
        );
        return new MessageDto(messages);
    }

    // 메세지 수정 (Update)
    @Transactional
    public Long updateMessage(MessageDto messageDto) {
        Messages messages = messageRepository.findById(messageDto.getMessageId()).orElseThrow(
                () -> new IllegalArgumentException("해당 메세지가 존재하지 않습니다.")
        );
        messages.update(messageDto);
        return messages.getMessageId();
    }

    // 메세지 삭제 (Delete)
    @Transactional
    public Long deleteMessage(Long messageId) {
        messageRepository.deleteById(messageId);
        return messageId;
    }
}
