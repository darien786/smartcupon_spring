package com.smartcupon.smartcupon.common.exceptions.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;


public class ValidationErrorHandler {
    
    public static Map<String, String> handler(MethodArgumentNotValidException ex){
        
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult()
            .getFieldErrors()
            .forEach(error ->
                errores.put(
                    error.getField(),
                    error.getDefaultMessage()
                )
            );

        return errores;
    }
}
