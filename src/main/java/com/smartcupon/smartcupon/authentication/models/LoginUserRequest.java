package com.smartcupon.smartcupon.authentication.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public class LoginUserRequest {
    
    @NotNull
    @NotBlank(message = "El usuario es obligatorio")
    @Size(min = 4, max = 30, message = "El usuario no es valido")
    private String username;

    @NotNull
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 18, message = "La contraseña no es valida")
    private String password;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
