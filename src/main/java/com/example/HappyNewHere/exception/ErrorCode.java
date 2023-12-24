package com.example.HappyNewHere.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(400,HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다"),
    INVALID_PERMISSION(401,HttpStatus.UNAUTHORIZED, "권한이 없습니다"),
    DUPLICATED_USER_NAME(400,HttpStatus.CONFLICT, "유저명이 중복되거나 null일 수 있습니다."),
    DATABASE_ERROR(400,HttpStatus.INTERNAL_SERVER_ERROR, "DB에러가 발생하였습니다");
    private int status;
    private HttpStatus error;
    private String message;
}