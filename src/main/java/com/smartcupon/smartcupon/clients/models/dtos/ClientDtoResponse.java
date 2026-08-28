package com.smartcupon.smartcupon.clients.models.dtos;

import java.time.LocalDate;

public class ClientDtoResponse {
    
    private Long idClient;
    private String name;
    private String paternalLastname;
    private String maternalLastname;
    private LocalDate birthDate;
    private String phone;
    private String email;
    
    // Address
    private AddressDtoResponse address;

    public ClientDtoResponse(Long idClient, String name, String paternalLastname, String maternalLastname,
            LocalDate birthDate, String phone, String email, AddressDtoResponse address) {
        this.idClient = idClient;
        this.name = name;
        this.paternalLastname = paternalLastname;
        this.maternalLastname = maternalLastname;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Long getIdClient(){
        return idClient;
    }

    public void setIdClient(Long idClient){
        this.idClient = idClient;
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

    public AddressDtoResponse getAddress() {
        return address;
    }

    public void setAddress(AddressDtoResponse address) {
        this.address = address;
    }    
}
