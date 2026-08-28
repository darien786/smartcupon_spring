package com.smartcupon.smartcupon.clients.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartcupon.smartcupon.clients.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
    
    Optional<Address> findByIdAddress(Long idAddress);
    
}
