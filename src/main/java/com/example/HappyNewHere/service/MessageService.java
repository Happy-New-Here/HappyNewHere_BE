package com.example.HappyNewHere.service;

import com.example.HappyNewHere.domain.Account;
import com.example.HappyNewHere.domain.Messages;
import com.example.HappyNewHere.dto.MessageDto;
import com.example.HappyNewHere.dto.request.MessageRequestDto;
import com.example.HappyNewHere.exception.ErrorCode;
import com.example.HappyNewHere.exception.HappyException;
import com.example.HappyNewHere.repository.AccountRepository;
import com.example.HappyNewHere.repository.MessageRepository;
import com.example.HappyNewHere.utils.AuthenticateUtils;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;
    private final AuthenticateUtils authenticateUtils;

    // 메세지 생성 (Create)
    public MessageDto createMessage(String userId, MessageRequestDto messageRequestDto) {
        Optional<Account> sender = accountRepository.findByUserId(userId);
        Optional<Account> receiver = accountRepository.findByUserId(messageRequestDto.getReceiver());
        if (sender.isEmpty()) throw new HappyException(ErrorCode.USER_NOT_FOUND);
        if (receiver.isEmpty()) throw new HappyException(ErrorCode.USER_NOT_FOUND);

        Messages messages = Messages.of(
                LocalDateTime.now(),
                messageRequestDto.getContext(),
                sender.get().getAccountId(),
                receiver.get().getAccountId(),
                messageRequestDto.getPaperNum(),
                messageRequestDto.isAnonymous()
        );
        messageRepository.save(messages);
        return MessageDto.from(messages);
    }

    // 내가 받은 메세지들 가져오기 (Read)
//    public List<MessageDto> findAllMessages(String receiver) {
//        try{
//            List<Messages> messagesList = messageRepository.findByReceiver(receiver);
//            List<MessageDto> messageDtoList = new ArrayList<>();
//            for (Messages messages : messagesList) {
//                messageDtoList.add(new MessageDto(messages));
//            }
//            return messageDtoList;
//        } catch (Exception e) {
////            throw new DBEmptyDataException("조회 실패!");
//        }
//        return null;
//    }
//
//    // 메세지 하나 가져오기 (Read)
//    public MessageDto findOneMessage(Long messageId) {
//        Messages messages = messageRepository.findById(messageId).orElseThrow(
//                () -> new IllegalArgumentException("조회 실패")
//        );
//        return new MessageDto(messages);
//    }
//
//    // 메세지 수정 (Update)
//    @Transactional
//    public Long updateMessage(MessageDto messageDto) {
//        Messages messages = messageRepository.findById(messageDto.getMessageId()).orElseThrow(
//                () -> new IllegalArgumentException("해당 메세지가 존재하지 않습니다.")
//        );
//        //messages.update(messageDto);
//        return messages.getMessageId();
//    }
//
//    // 메세지 삭제 (Delete)
//    @Transactional
//    public Long deleteMessage(Long messageId) {
//        messageRepository.deleteById(messageId);
//        return messageId;
//    }
}
