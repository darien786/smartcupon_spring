package com.smartcupon.smartcupon.users.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartcupon.smartcupon.common.exceptions.BadRequestException;
import com.smartcupon.smartcupon.users.models.dtos.UserDtoRequest;
import com.smartcupon.smartcupon.users.models.dtos.UserDtoResponse;
import com.smartcupon.smartcupon.users.services.UserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDtoResponse>> getFilterUsers(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String roleName,
        @RequestParam(required = false) String username) {
            
        return ResponseEntity.status(HttpStatus.OK).body(userService.findFilterAllUsers(name, roleName, username));
    }    

    @PostMapping()
    public ResponseEntity<Void> postInsertUser(@RequestBody @Valid UserDtoRequest userDto) {
        
        if (userDto == null) {
            
            throw new BadRequestException("La información del usuario es necesaria");
        }

        userService.saveUser(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<Void> putUpdateUser(@PathVariable Long idUser, @RequestBody @Valid UserDtoRequest userDto) {
        
        if(idUser <= 0){

            throw new BadRequestException("El identificador no es válido");
        }

        if(userDto == null){

            throw new BadRequestException("La información del usuario es necesaria");
        }

        userService.saveUser(userDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long idUser){

        if(idUser <= 0){

            throw new BadRequestException("El identificador no es válido");
        }

        userService.deleteUser(idUser);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
