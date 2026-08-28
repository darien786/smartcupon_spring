package com.smartcupon.smartcupon.security.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartcupon.smartcupon.clients.models.Client;
import com.smartcupon.smartcupon.clients.repositories.ClientRepository;
import com.smartcupon.smartcupon.security.client.CustomClientDetails;


@Service
public class CustomClientDetailsService implements UserDetailsService {
    
    private final ClientRepository clientRepository;

    public CustomClientDetailsService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Client> client = clientRepository.findByEmailWithAddress(email);

        if(!client.isPresent()){

            throw new UsernameNotFoundException("Usuario no encontrado");            
        }

        return new CustomClientDetails(client.get());
    }
}
