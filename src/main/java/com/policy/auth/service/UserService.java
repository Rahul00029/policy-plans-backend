package com.policy.auth.service;
import java.util.List;

import com.policy.auth.entities.User;

public interface UserService {
	
	public List<User> listOfUsers();
	public User authenticateUser(String username,String password);

}
