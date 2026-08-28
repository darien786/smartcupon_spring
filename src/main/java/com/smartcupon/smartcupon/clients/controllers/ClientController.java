package com.smartcupon.smartcupon.clients.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcupon.smartcupon.clients.models.dtos.ClientDtoRequest;
import com.smartcupon.smartcupon.clients.services.ClientService;
import com.smartcupon.smartcupon.common.exceptions.BadRequestException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    
    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PutMapping("/{email}")
    public ResponseEntity<Void> putUpdateClient(
            @PathVariable 
            @NotBlank(message = "El correo es obligatorio")
            @Email(message = "El correo no es valido")
            String email, 
            @Valid @RequestBody ClientDtoRequest clientRequest) {

        if(clientRequest == null){
            throw new BadRequestException("Información incompleta");
        }
        
        clientService.updateClient(email, clientRequest);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
        
}
