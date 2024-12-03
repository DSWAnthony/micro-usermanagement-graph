package com.api.backend.infrastructure.out.persistence.repository;

import com.api.backend.infrastructure.out.persistence.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity,String> {
}
