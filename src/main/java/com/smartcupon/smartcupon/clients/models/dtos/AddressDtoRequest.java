package com.smartcupon.smartcupon.clients.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class AddressDtoRequest {
    
    @NotNull
    @NotBlank(message = "La calle es obligatoria")
    @Size(min = 4, max = 30, message = "La calle no es valida")
    private String street;

    @NotNull
    @NotBlank(message = "El numero es obligatorio")
    @Positive(message = "El numero no es valido")
    private Integer number;

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }    
}
