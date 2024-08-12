package com.pranay.happ.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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