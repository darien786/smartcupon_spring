package com.smartcupon.smartcupon.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartcupon.smartcupon.users.repositories.UserRepository;
import com.smartcupon.smartcupon.security.user.CustomUserDetails;
import com.smartcupon.smartcupon.users.models.User;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<User> user = userRepository.findByUsernameWithRole(username);

        if(!user.isPresent() || user.get().getIsActive() == false){

            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return new CustomUserDetails(user.get());
    } 
}
