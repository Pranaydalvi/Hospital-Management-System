package com.pranay.happ.serviceI;

import com.pranay.happ.dto.Response;
import com.pranay.happ.entity.Appointment;

public interface AppointmentServiceI {

	Response bookAppointment(Appointment appointment, String usernumber);


}
