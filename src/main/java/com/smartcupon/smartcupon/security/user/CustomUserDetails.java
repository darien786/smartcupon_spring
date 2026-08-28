package com.smartcupon.smartcupon.security.user;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import com.smartcupon.smartcupon.users.models.User;

public class CustomUserDetails implements UserDetails{
    
    private final User user;

    public CustomUserDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return List.of(
            new SimpleGrantedAuthority(
                "ROLE_" + user.getRole().getName()
            )
        );
    }

    @Override
    public @Nullable String getPassword() {
        
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        
        return user.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(user.getIsActive());
    }
}
