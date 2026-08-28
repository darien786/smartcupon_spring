package com.smartcupon.smartcupon.common.responses;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    
    private HttpStatus status;
    private String code;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> errores;

    public ErrorResponse(HttpStatus status, String code, String message, LocalDateTime timestamp){
        this.status = status;
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ErrorResponse(HttpStatus status, String code, String message, LocalDateTime timestamp, Map<String, String> errores){
        this.status = status;
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
        this.errores = errores;
    }

    public HttpStatus getStatus(){
        return status;
    }

    public void setStatus(HttpStatus status){
        this.status = status;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public LocalDateTime getTimestamp(){
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp = timestamp;
    }

    public Map<String, String> getErrores() {
        return errores;
    }

    public void setErrores(Map<String, String> errores) {
        this.errores = errores;
    }
}
