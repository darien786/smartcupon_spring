package com.smartcupon.smartcupon.users.models.dtos;

public class UserDtoResponse {
    
    private Long idUser;
    private String name;
    private String paternalLastname;
    private String maternalLastname;
    private String curp;
    private String email;
    private String username;
    private RoleDtoResponse role;

    public UserDtoResponse(){}

    public UserDtoResponse(Long idUser, String name, String paternalLastname, String maternalLastname, String curp, String email, String username, RoleDtoResponse role){
        this.idUser = idUser;
        this.name = name;
        this.paternalLastname = paternalLastname;
        this.maternalLastname = maternalLastname;
        this.curp = curp;
        this.email = email;
        this.username = username;
        this.role = role;
    }

    public Long getIdUser(){
        return idUser;
    }

    public void setIdUser(Long idUser){
        this.idUser = idUser;
    }

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

    public RoleDtoResponse getRole(){
        return role;
    }

    public void setRole(RoleDtoResponse role){
        this.role = role;
    }
}
