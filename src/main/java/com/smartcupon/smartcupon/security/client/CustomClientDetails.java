package com.smartcupon.smartcupon.security.client;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.smartcupon.smartcupon.clients.models.Client;

public class CustomClientDetails implements UserDetails {

    private final Client client;

    public CustomClientDetails(Client client){
        this.client = client;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return List.of(
            new SimpleGrantedAuthority("ROLE_CLIENT")
        );
    }

    @Override
    public @Nullable String getPassword() {
        
        return client.getPasswordHash();
    }    

    @Override
    public String getUsername() {
        
        return client.getEmail();
    }

    @Override
    public boolean isEnabled(){
        return true;
    }

}
