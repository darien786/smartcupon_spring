package com.smartcupon.smartcupon.users.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RoleDtoRequest {

    @NotNull
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 4, max = 30)
    private String name;
    private Boolean isActive;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
