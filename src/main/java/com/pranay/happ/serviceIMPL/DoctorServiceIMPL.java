package com.pranay.happ.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranay.happ.dto.Response;
import com.pranay.happ.entity.AssignedDoctor;
import com.pranay.happ.repo.DoctorRepository;
import com.pranay.happ.serviceI.DoctorServiceI;
import com.pranay.happ.util.UserRequestIDGenerator;

@Service
public class DoctorServiceIMPL implements DoctorServiceI{
	
	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public Response saveDoctorData(AssignedDoctor ad) {
		Response response = new Response();
        try {
        	String doctornum = UserRequestIDGenerator.generateUserID();
        	ad.setDoctornumber(doctornum);
            AssignedDoctor assignedDoctor = doctorRepository.save(ad);
            
            if (assignedDoctor != null) {
                response.setMsg("Data inserted.");
            } else {
                response.setMsg("Data not get inserted.");
            }
        } catch (Exception e) {
            response.setMsg("Error occurred while assigning doctor data: " + e.getMessage());
        }
        return response;
    }

}
