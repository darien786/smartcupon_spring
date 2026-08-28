package com.smartcupon.smartcupon.users.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    
    private String name;
    
    @Column(name = "paternal_lastname")
    private String paternalLastname;
    
    @Column(name = "maternal_lastname")
    private String maternalLastname;
    
    @Column(unique = true, length = 18)
    private String curp;

    private String email;

    @Column(unique = true)
    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role")
    private Role role;

    public User(){}

    public User(Long idUser, String name, String paternalLastname, String maternalLastname, String curp, String email, String username, String passwordHash, Boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt, Role role){
        this.idUser = idUser;
        this.name = name;
        this.paternalLastname = paternalLastname;
        this.maternalLastname = maternalLastname;
        this.curp = curp;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = role;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }    

    
}
