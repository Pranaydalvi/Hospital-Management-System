package com.pranay.happ.serviceI;

import com.pranay.happ.dto.Response;
import com.pranay.happ.dto.ResponseDto;
import com.pranay.happ.dto.UserRequestDto;
import com.pranay.happ.entity.Login;
import com.pranay.happ.entity.UserRequest;

public interface UserServiceI {
	ResponseDto addUserRequest(Login login);

	UserRequest getUserByUserNumber(String unum);

	Response updateUserByUserNum(String unum, UserRequest user);

	Response deleteUserByEmail(String email);

	UserRequestDto getUserReuestAppointmentData(String usernumber);

}
