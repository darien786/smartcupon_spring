package com.smartcupon.smartcupon.users.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDtoRequest {

    @NotNull
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 30, message = "El nombre no es valido")
    private String name;

    @NotNull
    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 4, max = 30, message = "El apellido no es valido")
    private String paternalLastname;

    @NotNull
    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 4, max = 30, message = "El apellido no es valido")
    private String maternalLastname;

    @NotNull
    @NotBlank(message = "La curp es obligatoria")
    @Size(min = 18, max = 18, message = "La curp debe tener 18 caracteres" )
    private String curp;

    @NotNull
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no es valido")
    private String email;
    
    @NotNull
    @NotBlank(message = "El usuario es obligatorio")
    @Size(min = 4, max = 18, message = "El usuario no es valido")
    private String username;

    @NotNull
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 18, message = "La contraseña no es valida")
    private String newPassword;
    
    @NotNull
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 18, message = "La contraseña no es valida")
    private String confirmPassword;

    @NotNull(message = "El role es obligatorio")
    private RoleDtoRequest role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaternalLastname() {
        return paternalLastname;
    }

    public void setPaternalLastname(String paternalLastname) {
        this.paternalLastname = paternalLastname;
    }

    public String getMaternalLastname() {
        return maternalLastname;
    }

    public void setMaternalLastname(String maternalLastname) {
        this.maternalLastname = maternalLastname;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword(){
        return newPassword;
    }

    public void setNewPassword(String newPassword){
        this.newPassword = newPassword;
    }

    public String getConfirmPassword(){
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword){
        this.confirmPassword = confirmPassword;
    }

    public RoleDtoRequest getRole() {
        return role;
    }

    public void setRole(RoleDtoRequest role) {
        this.role = role;
    }    
}
