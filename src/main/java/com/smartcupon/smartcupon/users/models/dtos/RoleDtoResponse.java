package com.smartcupon.smartcupon.users.models.dtos;

public class RoleDtoResponse {
    
    private Long idRole;
    private String name;

    public RoleDtoResponse(Long idRole, String name){
        this.idRole = idRole;
        this.name = name;
    }

    public Long getIdRole(){
        return idRole;
    }

    public void setIdRole(Long idRole){
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
