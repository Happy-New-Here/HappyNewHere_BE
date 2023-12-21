package com.example.HappyNewHere.controller;


import com.example.HappyNewHere.dto.AccountDto;
import com.example.HappyNewHere.dto.UserInfo;
import com.example.HappyNewHere.service.AccountService;
import com.example.HappyNewHere.service.KakaoService;
import com.example.HappyNewHere.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class AccountController {

    private final AccountService accountService;
    private final KakaoService kakaoService;

    @GetMapping("/login/kakao")
    public ResponseEntity<?> login(
            @RequestParam("code") String code
    ) {
        System.out.println("dd");
        String accessToken = kakaoService.kakaoToken(code);
        System.out.println(accessToken);
        UserInfo userInfo = kakaoService.kakaoUser(accessToken);
        System.out.println(userInfo);
        String response = accountService.saveAccount(userInfo);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/userInfo")
    public ResponseEntity<?> personalInfo(Authentication authentication
    ) {
        accountService.findAccount(Long.parseLong(authentication.getName()));
        return ResponseEntity.ok().body(accountService.findAccount(Long.parseLong(authentication.getName())));
    }

    // 유저 id 뿐만 아니라 발급한 토큰 또한 필요함.
    @PostMapping("/personalInfo")
    public ResponseEntity<?> personalInfo(
            Authentication authentication,
            @RequestParam String userId,
            @RequestParam String stateMsg
    ) {
        accountService.addUserId(Long.parseLong(authentication.getName()), userId);
        accountService.addStateMsg(Long.parseLong(authentication.getName()), stateMsg);
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

//    @GetMapping("/test")
//    public ResponseEntity<?> test() {
//        return ResponseEntity.ok().body("test");
//    }

    //
}
