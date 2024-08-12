package com.pranay.happ.serviceIMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.pranay.happ.controller.LoginController;
import com.pranay.happ.dto.AppointmentDto;
import com.pranay.happ.dto.Response;
import com.pranay.happ.dto.ResponseDto;
import com.pranay.happ.dto.UserRequestDto;
import com.pranay.happ.entity.Appointment;
import com.pranay.happ.entity.Login;
import com.pranay.happ.entity.Role;
import com.pranay.happ.entity.UserRequest;
import com.pranay.happ.repo.AppointmentRepository;
import com.pranay.happ.repo.LoginRepository;
import com.pranay.happ.repo.RoleRepository;
import com.pranay.happ.repo.UserRepository;
import com.pranay.happ.serviceI.UserServiceI;
import com.pranay.happ.util.EmailSender;
import com.pranay.happ.util.UserRequestIDGenerator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceIMPL implements UserServiceI {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public ResponseDto addUserRequest(Login login) {
		ResponseDto responseDto = new ResponseDto();
		if (login.getEmail() == null) {
			responseDto.setMsg("Email cannot be null.");
			return responseDto;
		}
		Login existingLogin = loginRepository.findByEmail(login.getEmail());
		if (existingLogin != null) {
			responseDto.setMsg("User already exists.");
			return responseDto;
		}

		String userId = UserRequestIDGenerator.generateUserID();
		login.getUserRequest().setUsernumber(userId);
		login.getUserRequest().setStatus(true);
		Role role = roleRepository.findById(3).get();
		login.getUserRequest().setRole(role);
		Login login2 = loginRepository.save(login);

		if (login2 != null && login2.getId() > 0) {

			responseDto.setMsg("User Successfully Registered.");
			// Call the EmailSender to send the email
            EmailSender.sendEmail(javaMailSender, login.getEmail(), login.getUserRequest().getFirstname(), 
            		login.getUserRequest().getLastname(), login.getUserRequest().getUsernumber());
			return responseDto;
		} else {

			responseDto.setMsg("User Not Successfully Registered.");
			return responseDto;
		}

	}

	@Override
	public UserRequest getUserByUserNumber(String unum) {
		UserRequest user=userRepository.findByUsernumber(unum);
		log.debug("fetched User Request data : " + user);
		return user;
	}

	@Override
	public Response updateUserByUserNum(String unum, UserRequest user) {
		Response response = new Response();
        UserRequest existingUser = userRepository.findByUsernumber(unum);
        if (existingUser == null) {
            response.setMsg("User not found.");
            return response;
        }
        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setAddress(user.getAddress());
        existingUser.setZipcode(user.getZipcode());
        existingUser.setCountry(user.getCountry());
        existingUser.setGender(user.getGender());
        existingUser.setMobNumber(user.getMobNumber());
        userRepository.save(existingUser);
        response.setMsg("User data successfully updated.");
        return response;
    }

	@Override
	public Response deleteUserByEmail(String email) {
		Response response = new Response();
		Login login=loginRepository.findByEmail(email);
		if (login == null) {
            response.setMsg("User not found.");
            return response;
        }else {
        	loginRepository.delete(login);
        	response.setMsg("User successfully Deleted.");
            return response;
        }
	}

	@Override
	public UserRequestDto getUserReuestAppointmentData(String usernumber) {
		UserRequest user = userRepository.findByUsernumber(usernumber);
		List<Appointment> appointments = appointmentRepository.findByUserRequestUsernumber(usernumber);
		log.debug("Appointment List : " + appointments);
		UserRequestDto urt=new UserRequestDto();
		urt.setUsernumber(user.getUsernumber());
		urt.setAddress(user.getAddress());
		urt.setCountry(user.getCountry());
		urt.setFirstname(user.getFirstname());
		urt.setLastname(user.getLastname());
		urt.setGender(user.getGender());
		urt.setMobNumber(user.getMobNumber());
		urt.setZipcode(user.getZipcode());
		
		List<AppointmentDto> appointmentDtos = new ArrayList<>();
	    
	    for (Appointment appointment : appointments) {
	        AppointmentDto apd = new AppointmentDto();
	        apd.setAppintmentNumber(appointment.getAppointmentNumber());
	        apd.setPname(appointment.getPname());
	        apd.setDate(appointment.getDate());
	        apd.setTime(appointment.getTime());
	        apd.setAge(appointment.getAge());
	        apd.setGender(appointment.getGender());
	        apd.setCategory(appointment.getCategory());
	        apd.setAppointedDoctor(appointment.getAppointedDoctor());
	        apd.setReferredDoctor(appointment.getReferredDoctor());
	        apd.setLocation(appointment.getLocation());
	        apd.setMobileNumber(appointment.getMobileNumber());
	        apd.setEmail(appointment.getEmail());
	        apd.setBloodGroup(appointment.getBloodGroup());
	        apd.setVisitType(appointment.getVisitType());
	        apd.setProblemHistory(appointment.getProblemHistory());
	        apd.setZipcode(appointment.getZipcode());
	        apd.setDoctornumber(appointment.getDoctornumber());
	        appointmentDtos.add(apd);
	    }

	    urt.setAppointmentDtos(appointmentDtos);
		
		return urt;
}
}