package com.cognizant.auth.repositories;

import org.springframework.data.repository.CrudRepository;

import com.cognizant.auth.entities.User;

public interface UserRepository extends CrudRepository<User,String> {
    
}
