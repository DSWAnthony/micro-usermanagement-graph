package com.api.backend.infrastructure.out.persistence.mapper;

import com.api.backend.domain.model.User;
import com.api.backend.infrastructure.out.persistence.entity.UserEntity;

public class UserMapper {

    public static User entityToDomain(UserEntity userEntity){
        User user = new User();
        if (userEntity !=null){
            user.setId(userEntity.getId());
            user.setEmail(userEntity.getEmail());
            user.setUsername(userEntity.getUsername());
            user.setFirst_name(userEntity.getFirst_name());
            user.setLast_name(userEntity.getLast_name());
            user.setPassword(userEntity.getPassword());
            if (userEntity.getAddressEntity()!=null)
                user.setAddress(AddressMapper.entityToDomain(userEntity.getAddressEntity()));
        }
        return user;
    }

    public static UserEntity domainToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        if (user !=null){
            userEntity.setUsername(user.getUsername());
            userEntity.setLast_name(user.getFirst_name());
            userEntity.setEmail(user.getEmail());
            userEntity.setFirst_name(user.getFirst_name());
            userEntity.setPassword(user.getPassword());

        }
        return userEntity;
    }
}
