package com.example.HappyNewHere.service;


import com.example.HappyNewHere.domain.Account;
import com.example.HappyNewHere.dto.AccountDto;
import com.example.HappyNewHere.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public Account save(AccountDto accountDto) {
        return accountRepository.save(accountDto.toEntity());
    }

    public Page<AccountDto> searchAccounts(String string, Pageable pageable) {
        return accountRepository.findByUserIdContainingOrNicknameContaining(string, string, pageable).map(AccountDto::from);
    }
}
