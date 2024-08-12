package com.pranay.happ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pranay.happ.dto.Response;
import com.pranay.happ.dto.UserRequestDto;
import com.pranay.happ.entity.Login;
import com.pranay.happ.entity.UserRequest;
import com.pranay.happ.serviceI.UserServiceI;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping (value = "/api/user")
public class UserRequestController {
	
	
	@Autowired
	private UserServiceI userServiceI;
	
	@PostMapping(value = "/save",consumes = "application/json")
	public String registerUserRequest(@RequestBody Login login) {

		userServiceI.addUserRequest(login);
		return "Data Added.";
	}

	@GetMapping(value="/getUserByUserNumber")
	public ResponseEntity<UserRequest> getUserByUserNumber(@RequestParam String unum){
		log.info("User Request Data Fetching Start using usernumber : " + unum);
		UserRequest user=userServiceI.getUserByUserNumber(unum);
		return new ResponseEntity<UserRequest>(user,HttpStatus.FOUND);
	}
	
	@PutMapping(value="/updateUser/{unum}")
	public ResponseEntity<Response> updateUserByUserNum(@PathVariable String unum, @RequestBody UserRequest user) {
		Response response= userServiceI.updateUserByUserNum(unum,user); 
		if (response != null) {
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/deleteUser/{email}")
	public ResponseEntity<Response> deleteUserByEmail(@PathVariable String email){
		Response response= userServiceI.deleteUserByEmail(email); 
		if (response != null) {
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/userAllAppointment/{usernumber}")
	public ResponseEntity<UserRequestDto> getUserAppointDetails(@PathVariable String usernumber){
		log.info("Checking User number : " + usernumber);
		UserRequestDto urd=userServiceI.getUserReuestAppointmentData(usernumber);
		return new ResponseEntity<UserRequestDto>(urd,HttpStatus.FOUND);
	}
	}

