package com.cognizant.auth.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.auth.entities.User;
import com.cognizant.auth.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> listOfUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User authenticateUser(String username, String password) {
		List<User> users= listOfUsers();
		User userModel=new User();
		for(User user:users) {
			if(user.getUserName().equals(username) && user.getPassword().equals(password) && !user.isAccountLocked()) {
				userModel.setUserName(user.getUserName());
				userModel.setPassword(user.getPassword());
				userModel.setRole(user.getRole());
				userModel.setAccountLocked(user.isAccountLocked());
				break;
			}
		}
		return userModel;
	}

}