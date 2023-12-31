package com.example.HappyNewHere.service;

import com.example.HappyNewHere.domain.Account;
import com.example.HappyNewHere.domain.Calender;
import com.example.HappyNewHere.domain.Messages;
import com.example.HappyNewHere.dto.CalenderDto;
import com.example.HappyNewHere.dto.response.MsgResponseDto;
import com.example.HappyNewHere.exception.ErrorCode;
import com.example.HappyNewHere.exception.HappyException;
import com.example.HappyNewHere.repository.AccountRepository;
import com.example.HappyNewHere.repository.CalenderRepository;
import com.example.HappyNewHere.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalenderService {
    private final CalenderRepository calenderRepository;
    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    public CalenderDto showCalender(Long accountId, String userId){
        CalenderDto calenderDto = new CalenderDto();
        calenderDto.setMessagesList(new ArrayList<>());
//        //1. 달력스타일 담기
//        Optional<Account> account = accountRepository.findById(accountId);
//        if(account.isPresent()){
//            Optional<Calender> calender = calenderRepository.findById(account.get().getAccountId());
//            calender.ifPresent(calenderDto::setCalender);
//        }


        //2. 일치하는 멤버인지 확인
        Optional<Account> viewer = accountRepository.findByUserId(userId);
        if (viewer.isPresent() && viewer.get().getAccountId().equals(accountId)){
            calenderDto.setOwner(true);
        }
        else calenderDto.setOwner(false);

        //3. 메세지 목록 담기(일치하는 멤버가 아니거나 25일이 아니면 null로 두기)
        if(calenderDto.isOwner()
                // && LocalDateTime.now().getYear()==2024
        ){
            List<Messages> messages = messageRepository.findByReceiver(viewer.get().getAccountId());
            for(Messages msg : messages){
                calenderDto.getMessagesList().add(toMsgDto(msg));
            }
        }
        return calenderDto;
    }
    public MsgResponseDto toMsgDto(Messages messages){
        Optional<Account> account = accountRepository.findById(messages.getSender());
        if (account.isEmpty()) throw new HappyException(ErrorCode.USER_NOT_FOUND);
        return MsgResponseDto.of(messages,account.get().getUserId(),account.get().getNickname());
    }


    //TODO: 달력스타일 수정
    public void updateCalender(Long accountId){}
}
