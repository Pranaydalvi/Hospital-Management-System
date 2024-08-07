package com.pranay.happ.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranay.happ.dto.Response;
import com.pranay.happ.dto.UserResponseDto;
import com.pranay.happ.entity.Login;
import com.pranay.happ.entity.Role;
import com.pranay.happ.serviceI.LoginServiceI;
import com.pranay.happ.serviceI.RoleServiceI;
//http://localhost:
@RestController
@RequestMapping(value = "/api/admin")
public class LoginController {
	
	@Autowired
	private LoginServiceI loginServiceI;

	@PostMapping(value = "/login")
	public ResponseEntity<UserResponseDto> getLoginData(@RequestBody Login login){
		System.out.println("Login Data Check : " + login);
		UserResponseDto userResponseDto = loginServiceI.getLoginData(login.getEmail(), login.getPassword());
		return new ResponseEntity<UserResponseDto>(userResponseDto,HttpStatus.OK);
	}
	
}





