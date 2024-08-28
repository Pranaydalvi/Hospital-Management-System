package com.pranay.happ.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import com.pranay.happ.entity.Appointment;
import com.pranay.happ.serviceI.AppointmentServiceI;
import com.pranay.happ.serviceI.PdfGenerateService;

@RestController
@RequestMapping(value ="/api/all")
@CrossOrigin(origins = "http://localhost:4200/")
public class AppointmentController {

	@Autowired
	private AppointmentServiceI appointmentServiceI;
	
	@Autowired
	private PdfGenerateService pdfGenerateService;
	@PostMapping(value = "/BookAppointment/{usernumber}")
	public ResponseEntity<Response> insertAppointmentData(@RequestBody Appointment appointment,@PathVariable String usernumber){
		System.out.println("Appointment Data  : " + appointment);
		Response response = appointmentServiceI.bookAppointment(appointment,usernumber);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@GetMapping("/test-pdf")
	public void testPdfGeneration(HttpServletResponse response) throws IOException {
	    Map<String, Object> data = new HashMap<>();
	    data.put("appointmentNumber", "12345");
	    // Add more data if necessary
	    
	    pdfGenerateService.generatePdfFile("test", data, "test.pdf");
	}
}	