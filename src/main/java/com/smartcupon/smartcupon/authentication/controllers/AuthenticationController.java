package com.smartcupon.smartcupon.authentication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcupon.smartcupon.clients.models.dtos.ClientDtoRequest;
import com.smartcupon.smartcupon.clients.services.ClientService;
import com.smartcupon.smartcupon.common.exceptions.BadRequestException;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    
    private final ClientService clientService;

    public AuthenticationController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping("/clients/register")
    public ResponseEntity<Void> postInsertClient(
            @Valid 
            @RequestBody 
            ClientDtoRequest clientRequest) {
        
        if (clientRequest == null) {
            
            throw new BadRequestException("Información incompleta.");
        }

        clientService.saveClient(clientRequest);        

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
}
