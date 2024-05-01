package com.cognizant.repositories;

import org.springframework.data.repository.CrudRepository;
import com.cognizant.entities.PolicyType;

import java.util.Optional;

public interface PolicyTypeRepository extends CrudRepository<PolicyType,Integer> {
    Optional<PolicyType> findByType(String type);
}
