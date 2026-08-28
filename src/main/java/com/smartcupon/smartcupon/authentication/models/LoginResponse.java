package com.smartcupon.smartcupon.authentication.models;

public class LoginResponse {
    
    private String jwt;
    private String cookie;
    private String message;

    public LoginResponse(String jwt, String cookie, String message){
        this.jwt = jwt;
        this.cookie = cookie;
        this.message = message;
    }

    public String getJwt(){
        return jwt;
    }

    public void setJwt(String jwt){
        this.jwt = jwt;
    }

    public String getCookie(){
        return cookie;
    }

    public void setCookie(String cookie){
        this.cookie = cookie;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
