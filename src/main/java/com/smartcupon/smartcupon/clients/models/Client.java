package com.smartcupon.smartcupon.clients.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "clients")
public class Client {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long idClient;

    private String name;

    @Column( name = "paternal_lastname")
    private String paternalLastname;

    @Column( name = "maternal_lastname")
    private String maternalLastname;

    @Column( name = "birth_date")
    private LocalDate birthDate;

    private String phone;
    private String email;
    
    @Column( name = "password_hash")
    private String passwordHash;

    @Column( name = "created_at")
    private LocalDateTime createdAt;

    @Column( name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_address")
    private Address address;    

    public Client(){}

    public Client(Long idClient, String name, String paternalLastname, String maternalLastname, LocalDate birthDate, String phone, String email, String passwordHash, LocalDateTime createdAt, LocalDateTime updatedAt, Address address ){
        this.idClient = idClient;
        this.name = name;
        this.paternalLastname = paternalLastname;
        this.maternalLastname = maternalLastname;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.address = address;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
}
