package com.br.pedroarruda.ssm.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({AccessDeniedException.class})
    public Map<String, String> handleException(
            Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Error",ex.getMessage());
        return error;
    }


}
