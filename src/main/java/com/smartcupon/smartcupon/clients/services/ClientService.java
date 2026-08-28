package com.smartcupon.smartcupon.clients.services;

import java.util.List;

import com.smartcupon.smartcupon.clients.models.Client;
import com.smartcupon.smartcupon.clients.models.dtos.ClientDtoRequest;
import com.smartcupon.smartcupon.clients.models.dtos.ClientDtoResponse;

public interface ClientService {
    
    List<ClientDtoResponse> findAllClients();
    Client saveClient(ClientDtoRequest clientRequest);
    Client updateClient(String email, ClientDtoRequest clientRequest);

}
