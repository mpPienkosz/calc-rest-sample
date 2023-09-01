package com.example.pointcalculator.common;

import com.example.pointcalculator.common.exception.EntityNotFoundException;
import com.example.pointcalculator.common.exception.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.example.pointcalculator.common.exception.ErrorCode.ENTITY_NOT_FOUND;

@ControllerAdvice
public class ExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler({EntityNotFoundException.class})
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
        log.error(ex.getLocalizedMessage(), ex);

        return new ErrorResponse(ENTITY_NOT_FOUND);
    }
}
