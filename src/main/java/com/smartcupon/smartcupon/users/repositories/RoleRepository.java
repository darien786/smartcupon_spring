package com.smartcupon.smartcupon.users.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartcupon.smartcupon.users.models.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{
    
    Optional<Role> findByNameAndIsActive(String name, Boolean isActive);
    Optional<Role> findByIdRoleAndIsActive(Long idRole, Boolean isActive);
    List<Role> findAllByIsActive(Boolean isActive);

}