package com.smartcupon.smartcupon.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.smartcupon.smartcupon.authentication.models.LoginClientRequest;
import com.smartcupon.smartcupon.security.client.CustomClientDetails;
import com.smartcupon.smartcupon.security.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

public class JwtClientAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public JwtClientAuthenticationFilter(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        setFilterProcessesUrl("/api/auth/clients/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response ) throws AuthenticationException {
        
        LoginClientRequest loginRequest;

        try{
            
            loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginClientRequest.class);

        }catch(IOException e){

            throw new AuthenticationServiceException("No se pudo procesar la información de inicio de sesión", e);                           
        }

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);

        return authenticationManager.authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        CustomClientDetails clientDetails = (CustomClientDetails) authResult.getPrincipal();

        String email = clientDetails.getUsername();

        String token = jwtService.generateToken(email);

        response.addHeader(
            "Authorization",
            "Bearer " + token
        );
    }

}
