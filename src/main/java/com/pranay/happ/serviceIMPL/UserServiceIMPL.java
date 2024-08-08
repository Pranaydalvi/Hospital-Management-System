package com.pranay.happ.serviceIMPL;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.pranay.happ.dto.Response;
import com.pranay.happ.dto.ResponseDto;
import com.pranay.happ.entity.Login;
import com.pranay.happ.entity.Role;
import com.pranay.happ.entity.UserRequest;
import com.pranay.happ.repo.LoginRepository;
import com.pranay.happ.repo.RoleRepository;
import com.pranay.happ.repo.UserRepository;
import com.pranay.happ.serviceI.UserServiceI;
import com.pranay.happ.util.EmailSender;
import com.pranay.happ.util.UserRequestIDGenerator;

@Service
public class UserServiceIMPL implements UserServiceI {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
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

	
}