package com.example.HappyNewHere.service;


import com.example.HappyNewHere.domain.Account;
import com.example.HappyNewHere.dto.AccountDto;
import com.example.HappyNewHere.dto.UserInfo;
import com.example.HappyNewHere.dto.request.AccountRequestDto;
import com.example.HappyNewHere.repository.AccountRepository;
import com.example.HappyNewHere.utils.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;
    @Value("${jwt.secret}")
    private String secretKey;
    private Long expiredMs = 1000*60*60L; //한시간

    @Transactional
    public Account save(AccountDto accountDto) {
        return accountRepository.save(accountDto.toEntity());
    }

    public Page<AccountDto> searchAccounts(String string, Pageable pageable) {
        return accountRepository.findByUserIdContainingOrNicknameContaining(string, string, pageable).map(AccountDto::from);
    }

    public AccountDto findAccount(Long accountId) {
        return AccountDto.from(accountRepository.findById(accountId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        ));
    }

    @Transactional
    public AccountDto addUserId(Long accountId, String userId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );
        account.setUserId(userId);
        accountRepository.save(account);
        return AccountDto.from(account);
    }



    public String saveAccount(UserInfo userInfo){
        String responseToken;

        Optional<Account> existedAccount = accountRepository.findById(userInfo.getId());
        if(existedAccount.isPresent()){
            //이미 존재하는 회원
            responseToken = JwtUtils.createJwt(existedAccount.get().getAccountId(), secretKey, expiredMs);
        }
        else{
            Account account = new Account();
            account.setAccountId(userInfo.getId());
            account.setNickname(userInfo.getProperties().getNickname());
            account.setProfileImg(userInfo.getProperties().getProfile_image());

            accountRepository.save(account);
            responseToken = JwtUtils.createJwt(account.getAccountId(), secretKey, expiredMs);
        }

        return responseToken;
    }

    public void addStateMsg(long l, String stateMsg) {
        Account account = accountRepository.findById(l).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );
        account.setStateMsg(stateMsg);
        accountRepository.save(account);
    }

    // 유저 id와 AccountRequestDto를 받아서 해당 유저의 정보를 수정한다.
    public AccountDto updateAccount(Long accountId, AccountRequestDto accountRequestDto) {
        // AccountId로 유저가 존재하는 지 확인하고 없을시 예외 반환
        // 있다면 userId가 동일한지 확인하고 다르다면 예외 반환
        // 이미 존재하는 유저이며, userId가 동일하다면 해당 유저의 정보를 수정한다.
    }
}
