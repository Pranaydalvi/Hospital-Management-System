package com.pranay.happ.serviceI;

import com.pranay.happ.dto.UserResponseDto;

public interface LoginServiceI {

	UserResponseDto getLoginData(String uname,String pass);
	
}