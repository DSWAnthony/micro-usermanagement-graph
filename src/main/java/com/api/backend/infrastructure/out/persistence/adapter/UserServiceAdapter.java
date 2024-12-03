package com.api.backend.infrastructure.out.persistence.adapter;


import com.api.backend.application.ports.out.UserServicePort;
import com.api.backend.domain.model.User;
import com.api.backend.infrastructure.out.persistence.entity.AddressEntity;
import com.api.backend.infrastructure.out.persistence.entity.UserEntity;
import com.api.backend.infrastructure.out.persistence.mapper.AddressMapper;
import com.api.backend.infrastructure.out.persistence.mapper.UserMapper;
import com.api.backend.infrastructure.out.persistence.repository.AddressRepository;
import com.api.backend.infrastructure.out.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceAdapter implements UserServicePort {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public UserServiceAdapter(UserRepository userRepository,AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::entityToDomain)
                .toList();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId)
                .map(UserMapper::entityToDomain)
                .orElseThrow();
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = UserMapper.domainToEntity(user);

        // Guardar primero el usuario
        UserEntity savedUserEntity = userRepository.save(userEntity);

        // Si hay dirección, guardarla con la referencia al usuario
        if (user.getAddress() != null) {
            AddressEntity addressEntity = AddressMapper.domainToEntity(user.getAddress());
            addressEntity.setUser(savedUserEntity);
            addressRepository.save(addressEntity);
        }

        return UserMapper.entityToDomain(savedUserEntity);
    }

    @Override
    public User updateUser(String userId, User user) {
        UserEntity existingUserEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Actualizar campos del usuario
        existingUserEntity.setPassword(user.getPassword());
        existingUserEntity.setEmail(user.getEmail());
        existingUserEntity.setFirst_name(user.getFirst_name());
        existingUserEntity.setLast_name(user.getLast_name());
        existingUserEntity.setUsername(user.getUsername());

        // Guardar usuario actualizado
        UserEntity updatedEntity = userRepository.save(existingUserEntity);

        // Actualizar o crear dirección
        if (user.getAddress() != null) {
            // Mapear datos de la dirección
            AddressEntity updatedAddressEntity = AddressMapper.domainToEntity(user.getAddress());
            updatedAddressEntity.setUser(updatedEntity);

            // Guardar dirección
            addressRepository.save(updatedAddressEntity);
        }

        return UserMapper.entityToDomain(updatedEntity);
    }

    @Override
    public Boolean deleteUser(String userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Eliminar usuario
        userRepository.delete(userEntity);
        return true;
    }
}
