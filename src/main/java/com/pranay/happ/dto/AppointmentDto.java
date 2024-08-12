package com.pranay.happ.dto;

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
public class AppointmentDto {

	private String appintmentNumber;
	
	private String pname;
	
	private String date;
	
	private String time;
	
	private int age;
	
	private String gender;
	
	private String category;
	
	private String appointedDoctor;
	
	private String referredDoctor;
	
	private String location;
	
	private String mobileNumber;
	
	private String email;
	
	private String bloodGroup;
	
	private String visitType;
	
	private String problemHistory;
	
	private String zipcode;
	
	private String doctornumber;
}