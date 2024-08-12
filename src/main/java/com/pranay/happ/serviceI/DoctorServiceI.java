package com.pranay.happ.serviceI;

import com.pranay.happ.dto.Response;
import com.pranay.happ.entity.AssignedDoctor;

public interface DoctorServiceI {

	Response saveDoctorData(AssignedDoctor ad);

}
