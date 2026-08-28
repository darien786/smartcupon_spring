package com.smartcupon.smartcupon.users.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartcupon.smartcupon.common.exceptions.BadRequestException;
import com.smartcupon.smartcupon.common.exceptions.ConflictException;
import com.smartcupon.smartcupon.common.exceptions.ResourceNotFoundException;
import com.smartcupon.smartcupon.users.models.Role;
import com.smartcupon.smartcupon.users.models.User;
import com.smartcupon.smartcupon.users.models.dtos.RoleDtoResponse;
import com.smartcupon.smartcupon.users.models.dtos.UserDtoRequest;
import com.smartcupon.smartcupon.users.models.dtos.UserDtoResponse;
import com.smartcupon.smartcupon.users.repositories.RoleRepository;
import com.smartcupon.smartcupon.users.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    private List<UserDtoResponse> formatResponseList(List<User> users){

        return users.stream().map(
            user -> new UserDtoResponse(
                user.getIdUser(),
                user.getName(),
                user.getPaternalLastname(),
                user.getMaternalLastname(),
                user.getCurp(),
                user.getEmail(),
                user.getUsername(),
                new RoleDtoResponse(
                    user.getRole().getIdRole(),
                    user.getRole().getName())
            )
        ).toList();
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<UserDtoResponse> findFilterAllUsers(String name, String roleName, String username) {
        
        List<User> users;

        if(name != null && !name.isBlank()){

            users = userRepository.findByNameAndIsActive(name, true);

            return formatResponseList(users);
        }
        
        if(roleName != null && !roleName.isBlank()){

            users = userRepository.findAllByRoleNameAndIsActive(roleName, true);

            return formatResponseList(users);
        }

        if(username != null && !username.isBlank()){

            users = userRepository.findByUsernameAndIsActive(username, true);
         
            return formatResponseList(users);
        }
        
        users = userRepository.findAllByIsActive(true);

        return formatResponseList(users);
    }


    @Override
    @Transactional
    public User saveUser(UserDtoRequest user) {

        Optional<User> userCurpActive = userRepository.findByCurpAndIsActive(user.getCurp(), true);
        List<User> userUsernameActive = userRepository.findByUsernameAndIsActive(user.getUsername(), true);
        
        if (userCurpActive.isPresent()) {
            
            throw new ConflictException("La curp ya fue registrada");
        }
        
        if(!userUsernameActive.isEmpty()){
            throw new ConflictException("El usurname ya fue registrado");
        }

        Optional<Role> roleOptional = roleRepository.findByNameAndIsActive(user.getRole().getName(), true);
        
        if(!roleOptional.isPresent()){
            
            throw new ResourceNotFoundException("El role no existe");
        }

        if (user.getUsername().length() < 7) {
            
            throw new BadRequestException("El usuario invalido");
        }

        Optional<User> userOptionalFalse = userRepository.findByCurpAndIsActive(user.getCurp(), false);

        User newUser = new User();

        newUser.setName(user.getName());
        newUser.setPaternalLastname(user.getPaternalLastname());
        newUser.setMaternalLastname(user.getMaternalLastname());
        newUser.setEmail(user.getEmail());
        newUser.setCurp(user.getCurp());
        newUser.setUsername(user.getUsername());

        if(user.getNewPassword().length() > 7){

            throw new BadRequestException("La contraseña debe tener al menos 8 caracteres");
        }

        //Password Hasheado
        newUser.setPasswordHash(passwordEncoder.encode(user.getNewPassword()));
        
        newUser.setIsActive(true);
        newUser.setRole(roleOptional.get());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        if(userOptionalFalse.isPresent()){
            newUser.setIdUser(userOptionalFalse.get().getIdUser());
            newUser.setCreatedAt(userOptionalFalse.get().getCreatedAt());
        }

        return userRepository.save(newUser);

    }

    @Override
    @Transactional
    public User updateUser(Long idUser, UserDtoRequest user) {
        
        Optional<User> userOptional = userRepository.findByIdUserAndIsActive(idUser, true);
        
        if(!userOptional.isPresent()){
            
            throw new ResourceNotFoundException("El usuario no existe");
        }

        Optional<User> userCurpActive = userRepository.findByCurpAndIsActive(user.getCurp(), true);
        List<User> userUsernameActive = userRepository.findByUsernameAndIsActive(user.getUsername(), true);

        if(userCurpActive.isPresent() && !userCurpActive.get().getIdUser().equals(idUser)){

            throw new ConflictException("La curp ya fue registrada por otro usuario");
        }

        if(!userUsernameActive.isEmpty() && userUsernameActive.size() > 1){

            throw new ConflictException("El username ya fue registrado por otro usuario");
        }

        User userUpdate = userOptional.get();
        userUpdate.setName(user.getName());
        userUpdate.setPaternalLastname(user.getPaternalLastname());
        userUpdate.setMaternalLastname(user.getMaternalLastname());
        userUpdate.setCurp(user.getCurp());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setUsername(user.getUsername());

        return userRepository.save(userUpdate);
    }

    @Override
    @Transactional
    public User deleteUser(Long idUser) {
        
        Optional<User> userOptional = userRepository.findByIdUserAndIsActive(idUser, true);
        
        if(!userOptional.isPresent()){
            
            throw new ResourceNotFoundException("El usuario no existe");
        }

        User userDelete = userOptional.get();
        userDelete.setIsActive(false);

        return userRepository.save(userDelete);
    }

}
