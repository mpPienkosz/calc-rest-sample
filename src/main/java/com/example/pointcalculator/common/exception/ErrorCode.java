package com.example.pointcalculator.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    ENTITY_NOT_FOUND(1, "Entity not found");


    // for documentation purposes like more detailed descriptions or possible fix solutions
    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
