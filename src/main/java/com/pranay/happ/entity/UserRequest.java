package com.pranay.happ.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String usernumber;
	
	private String firstname;
	
	private String lastname;
	
	private String address;
	
	private int zipcode;
	
	private String country;
	
	private String gender;
	
	private String mobNumber;
	
	@OneToOne(cascade = CascadeType.DETACH)
	private Role role;
	

}