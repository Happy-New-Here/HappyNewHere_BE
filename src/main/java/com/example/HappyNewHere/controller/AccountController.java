package com.example.HappyNewHere.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

@RequiredArgsConstructor
public class AccountController {

    @PostMapping("/personalInfo")
    public ResponseEntity<?> personalInfo(
            @RequestParam String nickname,
            @RequestParam String id
    ) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/{account}")
    public ResponseEntity<String> findUsers() {
        return ResponseEntity.ok().build();
    }

    // createUser
    @GetMapping("/createAccount")
    public ResponseEntity<String> createAccount() {

        return ResponseEntity.ok().build();
    }

}
