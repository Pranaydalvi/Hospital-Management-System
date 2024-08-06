package com.pranay.happ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranay.happ.entity.Login;
import com.pranay.happ.entity.UserRequest;
import com.pranay.happ.serviceI.UserServiceI;

@RestController
@RequestMapping (value = "/api/user")
public class UserRequestController {
	
	
	@Autowired
	private UserServiceI userServiceI;
	
	@PostMapping(value = "/save",consumes = "application/json")
	public String registerUserRequest(@RequestBody Login login) {

		userServiceI.addUserRequest(login);
		return "Data Added.";
	}

}
