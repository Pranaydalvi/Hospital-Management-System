package com.pranay.happ.serviceI;

import java.util.List;

import com.pranay.happ.dto.Response;
import com.pranay.happ.entity.AssignedDoctor;

public interface DoctorServiceI {

	Response saveDoctorData(AssignedDoctor ad);
	
	AssignedDoctor findDoctor(String catogory,String doctorName);

	List<String> getAllCategories();

	List<AssignedDoctor> getDoctorsByCategory(String category);

}
