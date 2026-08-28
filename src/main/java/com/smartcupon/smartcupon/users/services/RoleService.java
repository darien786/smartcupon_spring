package com.smartcupon.smartcupon.users.services;

import java.util.List;

import com.smartcupon.smartcupon.users.models.dtos.RoleDtoRequest;
import com.smartcupon.smartcupon.users.models.dtos.RoleDtoResponse;
import com.smartcupon.smartcupon.users.models.Role;

public interface RoleService {

    List<RoleDtoResponse> findAllRoles();
    Role findByName(String name);
    Role findByIdRole(Long idRole);
    Role saveRole(RoleDtoRequest role);
    Role updateRole(Long idRole, RoleDtoRequest role);
    Role deleteRole(Long idRole);

}
