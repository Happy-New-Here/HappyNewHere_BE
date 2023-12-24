package com.example.HappyNewHere.service;


import com.example.HappyNewHere.domain.Account;
import com.example.HappyNewHere.dto.AccountDto;
import com.example.HappyNewHere.dto.UserInfo;
import com.example.HappyNewHere.dto.request.AccountRequestDto;
import com.example.HappyNewHere.exception.ErrorCode;
import com.example.HappyNewHere.exception.HappyException;
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
    private Long expiredMs = 1000*60*60*24*3L; //한시간

    @Transactional
    public Account save(AccountDto accountDto) {
        return accountRepository.save(accountDto.toEntity());
    }

    public Page<AccountDto> searchAccounts(String string, Pageable pageable) {
        return accountRepository.findByUserIdContainingOrNicknameContaining(string, string, pageable).map(AccountDto::from);
    }

    public AccountDto findAccount(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if(!account.isPresent())
            throw new HappyException(ErrorCode.USER_NOT_FOUND);
        return AccountDto.from(account.get());
    }

    @Transactional
    public AccountDto addUserId(Long accountId, String userId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if(!account.isPresent())
            throw new HappyException(ErrorCode.USER_NOT_FOUND);

        if(!checkUserId(userId,account.get()))
            throw new HappyException(ErrorCode.DUPLICATED_USER_NAME);

        account.get().setUserId(userId);
        accountRepository.save(account.get());
        return AccountDto.from(account.get());
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

            if (userInfo.getProperties().getProfile_image().equals("http://k.kakaocdn.net/dn/1G9kp/btsAot8liOn/8CWudi3uy07rvFNUkk3ER0/img_640x640.jpg")){
                if (Math.random()>=0.5)
                    account.setProfileImg("https://dang-na-dong.s3.ap-northeast-2.amazonaws.com/Snowman.png");
                else
                    account.setProfileImg("https://dang-na-dong.s3.ap-northeast-2.amazonaws.com/Presents.png");
            }
            else{
                account.setProfileImg(userInfo.getProperties().getProfile_image());
            }



            accountRepository.save(account);
            responseToken = JwtUtils.createJwt(account.getAccountId(), secretKey, expiredMs);
        }

        return responseToken;
    }

    public void addStateMsg(long accountId, String stateMsg) {
        Optional<Account> account = accountRepository.findById(accountId);
        if(!account.isPresent())
            throw new HappyException(ErrorCode.USER_NOT_FOUND);

        account.get().setStateMsg(stateMsg);
        accountRepository.save(account.get());
    }

    // 유저 id와 AccountRequestDto를 받아서 해당 유저의 정보를 수정한다.
    public AccountDto updateAccount(Long accountId, AccountRequestDto accountRequestDto) {
        Optional<Account> account = accountRepository.findById(accountId);
        if(!account.isPresent())
            throw new HappyException(ErrorCode.USER_NOT_FOUND);
        //중복된 유저 확인
        if(!checkUserId(accountRequestDto.userId(),account.get()))
            throw new HappyException(ErrorCode.DUPLICATED_USER_NAME);

        return AccountDto.from(accountRepository.save(Account.of(accountId, accountRequestDto, account.get().getProfileImg())));
    }

    //수정될userId 중복체크
    public boolean checkUserId(String userId, Account owner){
        if(userId==null || userId.equals(""))
            return false;
        Optional<Account> account = accountRepository.findByUserId(userId);
        if (account.isEmpty())
            return true;
        if(owner.getUserId().equals(userId))
            return true;

        return false;
    }
}
