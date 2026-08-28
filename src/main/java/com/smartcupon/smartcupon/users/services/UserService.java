package com.smartcupon.smartcupon.users.services;

import java.util.List;

import com.smartcupon.smartcupon.users.models.User;
import com.smartcupon.smartcupon.users.models.dtos.UserDtoRequest;
import com.smartcupon.smartcupon.users.models.dtos.UserDtoResponse;

public interface UserService {
    
    List<UserDtoResponse> findFilterAllUsers(String name, String roleName, String username);
    User saveUser(UserDtoRequest user);
    User updateUser(Long idUser, UserDtoRequest user);
    User deleteUser(Long idUser);
}
