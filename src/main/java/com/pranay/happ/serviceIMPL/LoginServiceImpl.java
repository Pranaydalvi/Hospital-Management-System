package com.pranay.happ.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranay.happ.dto.UserResponseDto;
import com.pranay.happ.entity.Login;
import com.pranay.happ.entity.UserRequest;
import com.pranay.happ.repo.LoginRepository;
import com.pranay.happ.serviceI.LoginServiceI;

@Service
public class LoginServiceImpl implements LoginServiceI {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public UserResponseDto getLoginData(String uname, String pass) {
		UserResponseDto userResponseDto = new UserResponseDto();
		Login login = loginRepository.findByEmailAndPassword(uname, pass);
		if (login != null && login.getUserRequest() != null) {
			UserRequest userRequest = login.getUserRequest();
			if (userRequest.isStatus()) {
				if (userRequest.getRole() != null) {
					userResponseDto.setUsernumber(userRequest.getUsernumber());
					userResponseDto.setFirstname(userRequest.getFirstname());
					userResponseDto.setLastname(userRequest.getLastname());
					userResponseDto.setEmail(login.getEmail());
					userResponseDto.setMobNumber(userRequest.getMobNumber());
					return userResponseDto;
				} else {
					userResponseDto.setErrorMsg("Access Denied, Please connect with your Administrator.");
					return userResponseDto;
				}
			} else {
				userResponseDto.setErrorMsg("Access Denied, Account is not active.");
				return userResponseDto;
			}
		} else {
			userResponseDto.setErrorMsg("User Does not Exists.");
			return userResponseDto;
		}
	}

	public UserResponseDto getLogin(String uname, String pass) {
		UserResponseDto userResponseDto = new UserResponseDto();
		Login login = loginRepository.findByEmail(uname);
		if (login != null) {
			if (login.getPassword() == pass) {
				UserRequest userRequest = login.getUserRequest();
				if (userRequest.isStatus()) {
					if (userRequest.getRole() != null) {
						userResponseDto.setUsernumber(userRequest.getUsernumber());
						userResponseDto.setFirstname(userRequest.getFirstname());
						userResponseDto.setLastname(userRequest.getLastname());
						userResponseDto.setEmail(login.getEmail());
						userResponseDto.setMobNumber(userRequest.getMobNumber());
						return userResponseDto;
					} else {
						userResponseDto.setErrorMsg("Access Denied, Please connect with your Administrator.");
						return userResponseDto;
					}
				} else {
					userResponseDto.setErrorMsg("Access Denied, Account is not active.");
					return userResponseDto;
				}
			} else {
				userResponseDto.setErrorMsg("Invalid Password");
				return userResponseDto;
			}
		} else {
			userResponseDto.setErrorMsg("User Does not Exists.");
			return userResponseDto;
		}
	}

}