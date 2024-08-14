package com.pranay.happ.dto;

import java.util.ArrayList; 
import java.util.List;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserRequestDto {

	private String usernumber;
	
	private String firstname;
	
	private String lastname;
	
	private String address;
	
	private int zipcode;
	
	private String country;
	
	private String gender;
	
	private String mobNumber;
	
	private boolean status;
	
	private List<AppointmentDto> appointmentDtos = new ArrayList<AppointmentDto>();
	
}