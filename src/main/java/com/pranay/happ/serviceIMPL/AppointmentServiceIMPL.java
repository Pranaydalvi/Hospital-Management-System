package com.pranay.happ.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranay.happ.dto.Response;
import com.pranay.happ.entity.Appointment;
import com.pranay.happ.entity.Role;
import com.pranay.happ.entity.UserRequest;
import com.pranay.happ.repo.AppointmentRepository;
import com.pranay.happ.repo.UserRepository;
import com.pranay.happ.serviceI.AppointmentServiceI;
import com.pranay.happ.util.UserRequestIDGenerator;

@Service
public class AppointmentServiceIMPL implements AppointmentServiceI{
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Response bookAppointment(Appointment appointment,String usernumber) {
		Response response = new Response();
        UserRequest user = userRepository.findByUsernumber(usernumber);
        if (user != null) {
            if (appointment.getUserRequest() != null) {
                UserRequest userRequest = appointment.getUserRequest();
                if (user.getUsernumber().equals(userRequest.getUsernumber())) {
                    String appointmentNumber = UserRequestIDGenerator.generateUserID();
                    String apponum=UserRequestIDGenerator.generateUserID();
                    appointment.setAppointmentNumber(apponum);
                    appointment.setUserRequest(user);
                    try {
                        Appointment savedAppointment = appointmentRepository.save(appointment);
                        if (savedAppointment != null) {
                            response.setMsg("Appointment Data inserted.");
                        } else {
                            response.setMsg("Appointment Data not inserted.");
                        }
                    } catch (Exception e) {
                        response.setMsg("Error occurred while inserting appointment data: " + e.getMessage());
                    }
                } else {
                    response.setMsg("User number mismatch.");
                }
            }
        } else {
            response.setMsg("User not found.");
        }
        return response;
    }
}
