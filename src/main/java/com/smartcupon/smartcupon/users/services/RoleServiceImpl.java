package com.smartcupon.smartcupon.users.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartcupon.smartcupon.common.exceptions.ConflictException;
import com.smartcupon.smartcupon.common.exceptions.ResourceNotFoundException;
import com.smartcupon.smartcupon.users.models.Role;
import com.smartcupon.smartcupon.users.models.dtos.RoleDtoRequest;
import com.smartcupon.smartcupon.users.models.dtos.RoleDtoResponse;
import com.smartcupon.smartcupon.users.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDtoResponse> findAllRoles() {
        
        List<Role> roles = roleRepository.findAllByIsActive(true);

        return roles.stream().map(
            role -> new RoleDtoResponse(
                role.getIdRole(), 
                role.getName())
        ).toList();
    }

    
    @Override
    @Transactional(readOnly = true)
    public Role findByIdRole(Long idRole) {
        
        Optional<Role> roleOptional = roleRepository.findByIdRoleAndIsActive(idRole, true);

        if(!roleOptional.isPresent()){

            throw new ResourceNotFoundException("El role no existe");
        }

        return roleOptional.get();
    }

    @Override
    @Transactional(readOnly = true)
    public Role findByName(String name) {
        
        Optional<Role> roleOptional = roleRepository.findByNameAndIsActive(name, true);

        if(!roleOptional.isPresent()){

            throw new ResourceNotFoundException("El role no existe");
        }

        return roleOptional.get();
    }


    @Override
    @Transactional
    public Role saveRole(RoleDtoRequest role) {
        
        Optional<Role> roleOptional = roleRepository.findByNameAndIsActive(role.getName(), true);

        if(roleOptional.isPresent()){

            throw new ConflictException("El role ya existe");
        }

        Role newRole = new Role();
        newRole.setName(role.getName());
        newRole.setIsActive(true);
        newRole.setCreatedAt(LocalDateTime.now());
        newRole.setUpdatedAt(LocalDateTime.now());

        return roleRepository.save(newRole);
    }


    @Override
    @Transactional
    public Role updateRole(Long idRole, RoleDtoRequest role) {
        
        Optional<Role> findRole = roleRepository.findByIdRoleAndIsActive(idRole, true);

        if(!findRole.isPresent()){
        
            throw new ResourceNotFoundException("El role no existe");
        }

        findRole = roleRepository.findByNameAndIsActive(role.getName(), true);

        if(findRole.isPresent()){

            throw new ConflictException("El role ya existe");
        }

        Role updateRole = findRole.get();
        updateRole.setName(role.getName());
        updateRole.setUpdatedAt(LocalDateTime.now());

        return roleRepository.save(updateRole);
    }

    @Override
    @Transactional
    public Role deleteRole(Long idRole) {
        
        Optional<Role> findRole = roleRepository.findByIdRoleAndIsActive(idRole, true);

        if(!findRole.isPresent()){
        
            throw new ResourceNotFoundException("El role no existe");
        }

        Role updateRole = findRole.get();

        updateRole.setIsActive(false);
        updateRole.setUpdatedAt(LocalDateTime.now());

        return roleRepository.save(updateRole);
    }

}

