package com.example.HappyNewHere.controller;


import com.example.HappyNewHere.service.CalenderService;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calender")
@RequiredArgsConstructor
public class CalenderController {
    private final CalenderService calenderService;


    @GetMapping("{userId}")
    public ResponseEntity showCalender(@PathVariable("userId") String userId){
        //TODO: 헤더에서 accountId 가져오기
        System.out.println(userId);
        Long accountId = 12345L;
        return ResponseEntity.ok().body(calenderService.showCalender(accountId,userId));
    }

    @PostMapping()
    public ResponseEntity updateCalender(){

        return ResponseEntity.ok().body(null);
    }

}
