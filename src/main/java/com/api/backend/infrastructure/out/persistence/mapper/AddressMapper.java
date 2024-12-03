package com.api.backend.infrastructure.out.persistence.mapper;

import com.api.backend.domain.model.Address;
import com.api.backend.infrastructure.out.persistence.entity.AddressEntity;

public class AddressMapper {

    public static AddressEntity domainToEntity(Address address){
        AddressEntity addressEntity = new AddressEntity();

        addressEntity.setAddress(address.getAddress());
        addressEntity.setCity(address.getCity());
        addressEntity.setCountry(address.getCountry());
        return addressEntity;
    }

    public static Address entityToDomain(AddressEntity addressEntity){
        Address address = new Address();
        address.setId(addressEntity.getId());
        address.setCity(addressEntity.getCity());
        address.setCountry(addressEntity.getCountry());
        address.setAddress(addressEntity.getAddress());
        return address;
    }
}
