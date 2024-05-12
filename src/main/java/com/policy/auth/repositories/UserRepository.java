package com.policy.auth.repositories;

import org.springframework.data.repository.CrudRepository;

import com.policy.auth.entities.User;

public interface UserRepository extends CrudRepository<User,String> {
    
}
