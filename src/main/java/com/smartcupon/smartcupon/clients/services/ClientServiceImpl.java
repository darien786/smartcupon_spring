package com.smartcupon.smartcupon.clients.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartcupon.smartcupon.clients.models.Address;
import com.smartcupon.smartcupon.clients.models.Client;
import com.smartcupon.smartcupon.clients.models.dtos.AddressDtoResponse;
import com.smartcupon.smartcupon.clients.models.dtos.ClientDtoRequest;
import com.smartcupon.smartcupon.clients.models.dtos.ClientDtoResponse;
import com.smartcupon.smartcupon.clients.repositories.AddressRepository;
import com.smartcupon.smartcupon.clients.repositories.ClientRepository;
import com.smartcupon.smartcupon.common.exceptions.ConflictException;
import com.smartcupon.smartcupon.common.exceptions.ResourceNotFoundException;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientRepository clientRepository, AddressRepository addressRepository, PasswordEncoder passwordEncoder){
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private List<ClientDtoResponse> formatResponseList(List<Client> clients){

        return clients.stream().map(
            client -> new ClientDtoResponse(
                client.getIdClient(),
                client.getName(),
                client.getPaternalLastname(),
                client.getMaternalLastname(),
                client.getBirthDate(),
                client.getPhone(),
                client.getEmail(),
                new AddressDtoResponse(
                    client.getAddress().getStreet(),
                    client.getAddress().getNumber()
                )
            )
        ).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDtoResponse> findAllClients() {
        
        return formatResponseList(clientRepository.findAll());
    }

    @Override
    @Transactional
    public Client saveClient(ClientDtoRequest clientRequest) {
        
        Optional<Client> findClient = clientRepository.findByEmail(clientRequest.getEmail());

        if(findClient.isPresent()){

            throw new ConflictException("El correo ya ha sido registrado");
        }

        Address address = new Address();
        address.setStreet(clientRequest.getAddress().getStreet());
        address.setNumber(clientRequest.getAddress().getNumber());
        address.setCreatedAt(LocalDateTime.now());
        address.setUpdatedAt(LocalDateTime.now());
        
        Address addressSave = addressRepository.save(address);

        Client client = new Client();
        client.setName(clientRequest.getName());
        client.setPaternalLastname(clientRequest.getPaternalLastname());
        client.setMaternalLastname(clientRequest.getMaternalLastname());
        client.setBirthDate(clientRequest.getBirthDate());
        client.setPhone(clientRequest.getPhone());
        client.setEmail(clientRequest.getEmail());
        client.setPasswordHash(passwordEncoder.encode(clientRequest.getPassword()));
        client.setAddress(addressSave);
        client.setCreatedAt(LocalDateTime.now());
        client.setUpdatedAt(LocalDateTime.now());
        
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client updateClient(String email, ClientDtoRequest clientRequest) {
        
        Optional<Client> findClient = clientRepository.findByEmail(clientRequest.getEmail());

        if(!findClient.isPresent()){

            throw new ResourceNotFoundException("El usuario no existe");
        }        

        Address updateAddress = findClient.get().getAddress();

        updateAddress.setStreet(clientRequest.getAddress().getStreet());
        updateAddress.setNumber(clientRequest.getAddress().getNumber());

        addressRepository.save(updateAddress);

        Client updateClient = findClient.get();
        updateClient.setName(clientRequest.getName());
        updateClient.setPaternalLastname(clientRequest.getPaternalLastname());
        updateClient.setMaternalLastname(clientRequest.getMaternalLastname());
        updateClient.setBirthDate(clientRequest.getBirthDate());
        updateClient.setPhone(clientRequest.getPhone());
        updateClient.setUpdatedAt(LocalDateTime.now());
        
        return clientRepository.save(updateClient);

    }
    
}
