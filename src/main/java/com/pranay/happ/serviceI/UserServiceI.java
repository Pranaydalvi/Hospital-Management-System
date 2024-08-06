package com.pranay.happ.serviceI;

import com.pranay.happ.dto.ResponseDto;
import com.pranay.happ.entity.Login;
import com.pranay.happ.entity.UserRequest;

public interface UserServiceI {
	ResponseDto addUserRequest(Login login);

}
