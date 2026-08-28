package com.smartcupon.smartcupon.security.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.smartcupon.smartcupon.security.service.CustomClientDetailsService;

@Configuration
public class AuthenticationConfig {
    
    @Bean
    @Primary
    public AuthenticationManager userAuthenticationManager(
            @Qualifier("customUserDetailsService")
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder){
        
        DaoAuthenticationProvider  provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(provider);
    }

    @Bean
    public AuthenticationManager clientAuthenticationManager(
            @Qualifier("customClientDetailsService")
            CustomClientDetailsService clientDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(clientDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(provider);
    }

}
