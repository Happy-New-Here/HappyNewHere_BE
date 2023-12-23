package com.example.HappyNewHere.utils;

import com.example.HappyNewHere.domain.Account;
import com.example.HappyNewHere.exception.ErrorCode;
import com.example.HappyNewHere.exception.HappyException;
import com.example.HappyNewHere.repository.AccountRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateUtils {
    private final AccountRepository accountRepository;

    public String getUserId(Authentication authentication){
        if (authentication==null) throw new HappyException(ErrorCode.USER_NOT_FOUND);
        Long accountId = Long.parseLong(authentication.getName());
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty())
            throw new HappyException(ErrorCode.USER_NOT_FOUND);

        return account.get().getUserId();
    }

    public Long getLongId(Authentication authentication){
        if (authentication==null) throw new HappyException(ErrorCode.USER_NOT_FOUND);
        Long accountId = Long.parseLong(authentication.getName());

        return accountId;
    }

}
