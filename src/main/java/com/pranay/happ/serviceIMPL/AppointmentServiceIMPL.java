package com.pranay.happ.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.pranay.happ.constant.Constants;
import com.pranay.happ.dto.Response;
import com.pranay.happ.entity.Appointment;
import com.pranay.happ.entity.AssignedDoctor;
import com.pranay.happ.entity.Role;
import com.pranay.happ.entity.UserRequest;
import com.pranay.happ.repo.AppointmentRepository;
import com.pranay.happ.repo.DoctorRepository;
import com.pranay.happ.repo.UserRepository;
import com.pranay.happ.serviceI.AppointmentServiceI;
import com.pranay.happ.util.EmailSender;
import com.pranay.happ.util.UserRequestIDGenerator;

@Service
public class AppointmentServiceIMPL implements AppointmentServiceI {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public Response bookAppointment(Appointment appointment, String usernumber) {
	    Response response = new Response();

	    try {
	        UserRequest user = userRepository.findByUsernumber(usernumber);
	        if (user == null) {
	            response.setMsg("User not found.");
	            return response;
	        }

	        appointment.setUserRequest(user);

	        AssignedDoctor assignedDoctor = doctorRepository.findByCatogoryAndName(appointment.getCategory(), appointment.getAppointedDoctor());
	        if (assignedDoctor == null) {
	            response.setMsg("Assigned doctor not found.");
	            return response;
	        }

	        boolean isDoctorAvailable = appointmentRepository.existsByAppointedDoctorAndDateAndTime(
	            assignedDoctor.getDoctornumber(), 
	            appointment.getDate(), 
	            appointment.getTime()
	        );

	        if (isDoctorAvailable) {
	            appointment.setStatus(Constants.NEW);
	        } else {
	            appointment.setStatus(Constants.PENDING);
	        }

	        String apponum = UserRequestIDGenerator.generateUserID();
	        appointment.setAppointmentNumber(apponum);

	        Appointment savedAppointment = appointmentRepository.save(appointment);
	        if (savedAppointment != null) {
	            response.setMsg("Appointment Data inserted with status: " + appointment.getStatus());
	        } else {
	            response.setMsg("Appointment Data not inserted.");
	        }
	    } catch (Exception e) {
	        response.setMsg("An error occurred: " + e.getMessage());
	        e.printStackTrace();  // Print the stack trace to the server logs for debugging
	    }

	    return response;
	}


}
