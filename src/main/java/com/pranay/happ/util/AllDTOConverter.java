package com.pranay.happ.util;

import java.util.ArrayList;
import java.util.List;

import com.pranay.happ.dto.AppointmentDto;
import com.pranay.happ.dto.UserRequestDto;
import com.pranay.happ.entity.Appointment;
import com.pranay.happ.entity.UserRequest;

public class AllDTOConverter {
	
	public static List<AppointmentDto> convertAppointmentDto(List<Appointment> appointments){
		
		List<AppointmentDto> appointmentDtos = new ArrayList<AppointmentDto>();
		
		for(Appointment appointment : appointments) {
			appointmentDtos.add(AppointmentDto.builder()
					             .appintmentNumber(appointment.getAppointmentNumber())
					             .pname(appointment.getPname())
					             .date(appointment.getDate())
					             .time(appointment.getTime())
					             .age(appointment.getAge())
					             .gender(appointment.getGender())
					             .category(appointment.getCategory())
					             .appointedDoctor(appointment.getAppointedDoctor())
					             .referredDoctor(appointment.getReferredDoctor())
					             .location(appointment.getLocation())
					             .email(appointment.getEmail())
					             .mobileNumber(appointment.getMobileNumber())
					             .bloodGroup(appointment.getBloodGroup())
					             .visitType(appointment.getVisitType())
					             .problemHistory(appointment.getProblemHistory())
					             .build());
		}
		
		return appointmentDtos;
	}
	
	public static UserRequestDto convertUserRequestDto(UserRequest userRequest) {
		return UserRequestDto.builder()
				.usernumber(userRequest.getUsernumber())
				.address(userRequest.getAddress())
				.country(userRequest.getCountry())
				.firstname(userRequest.getFirstname())
				.lastname(userRequest.getLastname())
				.gender(userRequest.getGender())
				.mobNumber(userRequest.getMobNumber())
				.zipcode(userRequest.getZipcode())
				.build();
	}

}