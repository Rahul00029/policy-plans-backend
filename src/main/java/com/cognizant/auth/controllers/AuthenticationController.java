package com.cognizant.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cognizant.auth.entities.User;
import com.cognizant.auth.service.UserService;

@RestController
@RequestMapping("api/authenticate")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
	@Autowired
	private UserService userService;
	
	@PostMapping("users")
	public ResponseEntity<?> authenticate(@RequestBody User userRequest){
		User user=userService.authenticateUser(userRequest.getUserName(), userRequest.getPassword());
		if(user.getUserName()!=null) {
			return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

}