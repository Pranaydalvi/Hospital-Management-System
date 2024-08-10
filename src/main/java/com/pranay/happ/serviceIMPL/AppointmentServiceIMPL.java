package com.pranay.happ.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.pranay.happ.dto.Response;
import com.pranay.happ.entity.Appointment;
import com.pranay.happ.entity.Role;
import com.pranay.happ.entity.UserRequest;
import com.pranay.happ.repo.AppointmentRepository;
import com.pranay.happ.repo.UserRepository;
import com.pranay.happ.serviceI.AppointmentServiceI;
import com.pranay.happ.util.EmailSender;
import com.pranay.happ.util.UserRequestIDGenerator;

@Service
public class AppointmentServiceIMPL implements AppointmentServiceI{
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public Response bookAppointment(Appointment appointment, String usernumber) {
Response response = new Response();
        
        // Find the user by usernumber
        UserRequest user = userRepository.findByUsernumber(usernumber);
        
        if (user != null) {
            System.out.println("User found: " + user.getUsernumber());

            // Associate the appointment with the found user
            appointment.setUserRequest(user);

            try {
                // Generate a unique appointment number
                String apponum = UserRequestIDGenerator.generateUserID();
                appointment.setAppointmentNumber(apponum);

                // Save the appointment
                Appointment savedAppointment = appointmentRepository.save(appointment);

                // Check if the appointment was saved successfully
                if (savedAppointment != null) {
                    // Send appointment confirmation email
                    EmailSender.sendAppointmentConfirmationEmail(javaMailSender, savedAppointment);
                    response.setMsg("Appointment Data inserted.");
                } else {
                    response.setMsg("Appointment Data not inserted.");
                }
            } catch (Exception e) {
                response.setMsg("Error occurred while inserting appointment data: " + e.getMessage());
            }
        } else {
            response.setMsg("User not found.");
        }

        System.out.println("Response Message: " + response.getMsg());
        return response;
    }
	}
