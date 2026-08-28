package com.smartcupon.smartcupon.clients.models.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class ClientDtoRequest {
    
    @NotNull
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 30, message = "El nombre no es valido")
    private String name;

    @NotNull
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 30, message = "El nombre no es valido")
    private String paternalLastname;

    @NotNull
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 30, message = "El nombre no es valido")
    private String maternalLastname;

    @NotNull(message = "La fecha es obligatoria")
    @Past(message = "La fecha no es valida")
    private LocalDate birthDate;

    @NotNull
    @NotBlank(message = "El telefono es obligatorio")
    @Size(min = 10, max = 10, message = "El telefono no es valido")
    private String phone;

    @NotNull
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no es valido")
    private String email;

    @NotNull
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 18, message = "La contraseña no es valida.")
    private String password;

    // Address
    @NotNull(message = "La direccion es obligatoria")
    private AddressDtoRequest address;

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AddressDtoRequest getAddress(){
        return address;
    }

    public void setAddress(AddressDtoRequest address){
        this.address = address;
    }

}
