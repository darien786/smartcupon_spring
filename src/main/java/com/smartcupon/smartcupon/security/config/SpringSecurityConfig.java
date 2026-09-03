package com.smartcupon.smartcupon.security.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.smartcupon.smartcupon.security.filter.JwtAuthorizationFilter;
import com.smartcupon.smartcupon.security.filter.JwtClientAuthenticationFilter;
import com.smartcupon.smartcupon.security.filter.JwtUserAuthenticationFilter;
import com.smartcupon.smartcupon.security.service.CustomUserDetailsService;
import com.smartcupon.smartcupon.security.service.JwtService;


@Configuration
public class SpringSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(
            HttpSecurity http,
            @Qualifier("userAuthenticationManager")
            AuthenticationManager userAuthenticationManager,
            @Qualifier("clientAuthenticationManager")
            AuthenticationManager clientAuthenticationManager,
            JwtService jwtService,
            CustomUserDetailsService customUserDetailsService) throws Exception {

        JwtUserAuthenticationFilter userFilter = new JwtUserAuthenticationFilter(userAuthenticationManager, jwtService);

        JwtClientAuthenticationFilter clientFilter = new JwtClientAuthenticationFilter(clientAuthenticationManager, jwtService);


        JwtAuthorizationFilter jwtAuthorizationFilter = 
                new JwtAuthorizationFilter(
                    jwtService, 
                    customUserDetailsService
                );

        
        return http
                .authenticationManager(userAuthenticationManager)
                .authorizeHttpRequests(
                    (auth) -> auth
                        .requestMatchers("/api/auth/users/login").permitAll()
                        .requestMatchers("/api/auth/clients/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/auth/clients/register").permitAll()
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        //Para cuando son ambos
                        //.requestMatchers("/api/auth/users").hasAnyRole("ADMIN")
                        .anyRequest().denyAll()
                )
                .addFilterBefore(
                    userFilter,
                    UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(
                    clientFilter,
                    UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(
                    jwtAuthorizationFilter, 
                    UsernamePasswordAuthenticationFilter.class)
                .cors(config -> {})
                .csrf(config -> config.disable())
                .sessionManagement(
                    management -> management
                        .sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS
                        )
                )
                .build();
    }    
}
