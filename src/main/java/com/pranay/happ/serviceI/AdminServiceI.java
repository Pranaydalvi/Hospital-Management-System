package com.pranay.happ.serviceI;

import com.pranay.happ.dto.Response;

public interface AdminServiceI {
	
	Response assignRole(String email,String rolename);

}