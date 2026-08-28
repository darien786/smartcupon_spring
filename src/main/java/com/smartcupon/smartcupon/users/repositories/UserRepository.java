package com.smartcupon.smartcupon.users.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smartcupon.smartcupon.users.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
    List<User> findAllByIsActive(Boolean isActive);
    
    List<User> findAllByRoleNameAndIsActive(String roleName, Boolean isActive);

    Optional<User> findByIdUserAndIsActive(Long idUser, Boolean isActive);
    List<User> findByNameAndIsActive(String name, Boolean isActive);
    Optional<User> findByCurpAndIsActive(String curp, Boolean isActive);
    List<User> findByUsernameAndIsActive(String username, Boolean isActive);    
    Optional<User> findByUsername(String username);    
    
    @Query("SELECT u FROM User u JOIN FETCH u.role WHERE u.username = :username AND u.isActive = true")
    Optional<User> findByUsernameWithRole(String username);
}
