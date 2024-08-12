package com.pranay.happ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranay.happ.dto.Response;
import com.pranay.happ.entity.AssignedDoctor;
import com.pranay.happ.entity.Role;
import com.pranay.happ.serviceI.DoctorServiceI;
import com.pranay.happ.serviceI.RoleServiceI;

@RestController
@RequestMapping(value ="/api/doctor")
public class DoctorController {

	@Autowired
	private DoctorServiceI doctorServiceI;
	
	@PostMapping(value = "/AssignedDoctor")
	public ResponseEntity<Response> insertRoleData(@RequestBody AssignedDoctor ad ){
		Response response = doctorServiceI.saveDoctorData(ad);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
