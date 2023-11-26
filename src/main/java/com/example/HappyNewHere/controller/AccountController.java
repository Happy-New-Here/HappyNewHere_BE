package com.example.HappyNewHere.controller;


import com.example.HappyNewHere.dto.AccountDto;
import com.example.HappyNewHere.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    // 유저 id 뿐만 아니라 발급한 토큰 또한 필요함.
    @PostMapping("/personalInfo")
    public ResponseEntity<?> personalInfo(
            @RequestParam String userId
    ) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/{account}")
    public ResponseEntity<Page<AccountDto>> findUsers(
            @PathVariable String account,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {

        return ResponseEntity.ok(accountService.searchAccounts(account,pageable));
    }

    // 유저 생성 (테스트용)
    @PostMapping("/createAccount")
    public ResponseEntity<AccountDto> createAccount(
            @RequestBody AccountDto accountDto
    ) {
        // ResponseEntity에 AccountDto를 담아서 반환
        return ResponseEntity.ok(AccountDto.from(accountService.save(accountDto)));
    }
}
