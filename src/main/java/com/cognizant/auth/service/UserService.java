package com.cognizant.auth.service;
import java.util.List;

import com.cognizant.auth.entities.User;

public interface UserService {
	
	public List<User> listOfUsers();
	public User authenticateUser(String username,String password);

}
