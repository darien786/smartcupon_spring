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
import org.springframework.web.bind.annotation.RestController;

import com.smartcupon.smartcupon.common.exceptions.BadRequestException;
import com.smartcupon.smartcupon.users.models.Role;
import com.smartcupon.smartcupon.users.models.dtos.RoleDtoRequest;
import com.smartcupon.smartcupon.users.models.dtos.RoleDtoResponse;
import com.smartcupon.smartcupon.users.services.RoleService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users/roles")
public class RoleController {


    private final RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDtoResponse>> getAllRoles(){

        return ResponseEntity.status(HttpStatus.OK).body(roleService.findAllRoles());
    }

    @GetMapping("/{name}")
    public ResponseEntity<RoleDtoResponse> getRoleByName(@PathVariable String name){
        
        if(name.isBlank() || name.length() < 4){
            throw new BadRequestException("El nombre no es valido");
        }

        // Esto hay que modificarlo
        Role role = roleService.findByName(name);

        return ResponseEntity.status(HttpStatus.OK).body(new RoleDtoResponse(role.getIdRole(), role.getName()));
    }

    @PostMapping()
    public ResponseEntity<Void> postInsertRole(@RequestBody @Valid RoleDtoRequest role) {
        
        if (role == null) {
            
            throw new BadRequestException("La información del role es requerida");
        }

        roleService.saveRole(role);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{idRole}")
    public ResponseEntity<Void> putUpdateRole(@PathVariable Long idRole, @RequestBody @Valid RoleDtoRequest role) {

        if(idRole <= 0){
            throw new BadRequestException("El identificador no es valido");
        }

        if( role == null){

            throw new BadRequestException("La información del role es requerida");
        }
        
        roleService.updateRole(idRole, role);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
        
    @DeleteMapping("/{idRole}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long idRole){

        if(idRole <= 0){
            throw new BadRequestException("El identificador no es valido");
        }

        roleService.deleteRole(idRole);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
