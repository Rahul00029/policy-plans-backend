package com.policy.repositories;

import org.springframework.data.repository.CrudRepository;
import com.policy.entities.PolicyType;

import java.util.Optional;

public interface PolicyTypeRepository extends CrudRepository<PolicyType,Integer> {
    Optional<PolicyType> findByType(String type);
}
