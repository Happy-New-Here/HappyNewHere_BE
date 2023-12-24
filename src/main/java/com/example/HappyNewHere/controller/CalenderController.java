package com.example.HappyNewHere.controller;


import com.example.HappyNewHere.service.CalenderService;
import com.example.HappyNewHere.utils.AuthenticateUtils;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calender")
@RequiredArgsConstructor
@CrossOrigin
public class CalenderController {
    private final CalenderService calenderService;
    private final AuthenticateUtils authenticateUtils;

    @GetMapping("{userId}")
    public ResponseEntity showCalender(
            @PathVariable(name = "userId") String userId,
            Authentication authentication){

        //TODO: 헤더에서 accountId 가져오기
        Long accountId = authenticateUtils.getLongId(authentication);
        return ResponseEntity.ok().body(calenderService.showCalender(accountId,userId));
    }

    @PostMapping()
    public ResponseEntity updateCalender(){
        return ResponseEntity.ok().body(null);
    }

}
