package com.pranay.happ.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@CrossOrigin(origins = "http://localhost:4200/")
public class DoctorController {

	@Autowired
	private DoctorServiceI doctorServiceI;
	
	@PostMapping(value = "/AssignedDoctor")
	public ResponseEntity<Response> insertRoleData(@RequestBody AssignedDoctor ad ){
		Response response = doctorServiceI.saveDoctorData(ad);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/categories")
    public List<String> getAllCategories() {
        return doctorServiceI.getAllCategories();
    }

    @GetMapping("/{category}")
    public List<AssignedDoctor> getDoctorsByCategory(@PathVariable String category) {
        return doctorServiceI.getDoctorsByCategory(category);
    }
}
