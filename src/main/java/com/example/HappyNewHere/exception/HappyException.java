package com.example.HappyNewHere.exception;


import lombok.Getter;

@Getter
public class HappyException extends RuntimeException{

    private int result;
    private ErrorCode errorCode;
    private String message;

    public HappyException(ErrorCode errorCode) {
        this.result = errorCode.getStatus();
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}