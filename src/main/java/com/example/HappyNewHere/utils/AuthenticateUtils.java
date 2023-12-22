package com.example.HappyNewHere.utils;

import com.example.HappyNewHere.domain.Account;
import com.example.HappyNewHere.exception.ErrorCode;
import com.example.HappyNewHere.exception.HappyException;
import com.example.HappyNewHere.repository.AccountRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateUtils {
    private final AccountRepository accountRepository;
    public String getAccount(Authentication authentication){
        if (authentication==null) throw new HappyException(ErrorCode.USER_NOT_FOUND);
        Long accountId = Long.parseLong(authentication.getName());
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty())
            throw new HappyException(ErrorCode.USER_NOT_FOUND);

        return account.get().getUserId();
    }

}