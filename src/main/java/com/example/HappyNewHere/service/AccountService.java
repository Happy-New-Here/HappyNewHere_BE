package com.example.HappyNewHere.service;


import com.example.HappyNewHere.domain.Account;
import com.example.HappyNewHere.dto.AccountDto;
import com.example.HappyNewHere.repository.AccountRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class AccoutService {
    AccountRepository accountRepository;

    public Account save(AccountDto accountDto) {
        return accountRepository.save(accountDto.toEntity());
    }
}
