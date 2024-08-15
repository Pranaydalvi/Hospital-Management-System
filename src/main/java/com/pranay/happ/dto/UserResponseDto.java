package com.pranay.happ.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.pranay.happ.entity.UserRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponseDto {
	

	private String usernumber;
	
	private String firstname;
	
	private String lastname;
	
	private String mobNumber;
	
	private String email;
	
	private String errorMsg;
	
	private String rolename;

}