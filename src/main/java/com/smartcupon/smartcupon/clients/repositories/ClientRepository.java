package com.smartcupon.smartcupon.clients.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smartcupon.smartcupon.clients.models.Client;


public interface ClientRepository extends JpaRepository<Client, Long>{


    
    Optional<Client> findByEmail(String email);
    Optional<Client> findByIdClient(Long idClient);

    
    // Login
    Optional<Client> findByEmailAndPasswordHash(String email, String passwordHash);

    @Query("SELECT c FROM Client c JOIN FETCH c.address WHERE c.email = :email")
    Optional<Client> findByEmailWithAddress(String email);
}
