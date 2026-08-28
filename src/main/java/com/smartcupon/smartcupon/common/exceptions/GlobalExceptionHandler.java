package com.smartcupon.smartcupon.common.exceptions;

import java.util.Map;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.smartcupon.smartcupon.common.exceptions.handler.ValidationErrorHandler;
import com.smartcupon.smartcupon.common.responses.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //400
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handlerBadRequest(BadRequestException ex){
        
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST,
            "BAD_REQUEST",
            ex.getMessage(),
            LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerValidationErrors(MethodArgumentNotValidException ex){

        Map<String, String> errores = ValidationErrorHandler.handler(ex);

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST,
            "VALIDATION_ERROR",
            "Los datos enviados no son validos",
            LocalDateTime.now(),
            errores
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    //401
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handlerUnauthorized(UnauthorizedException ex){

        
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.UNAUTHORIZED,
            "UNAUTHORIZED",
            ex.getMessage(),
            LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
    //403
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handlerForbidden(ForbiddenException ex){
        
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.FORBIDDEN,
            "FORBIDDEN",
            ex.getMessage(),
            LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    // 404 NOT FOUND
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex){

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.NOT_FOUND,
            "RESOURCE_NOT_FOUND",
            ex.getMessage(),
            LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    //409
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflic(ConflictException ex){

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.CONFLICT,
            "CONFLICT",
            ex.getMessage(),
            LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }


    //500 INTERNAL SERVER ERROR
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception ex){

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "INTERNAL_SERVER_ERROR",
            "Ocurrio un error, favor de intentarlo mas tarde",
            LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
