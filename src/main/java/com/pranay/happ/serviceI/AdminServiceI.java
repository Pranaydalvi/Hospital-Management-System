package com.pranay.happ.serviceI;

import java.util.List;

import com.pranay.happ.dto.Response;

public interface AdminServiceI {
	
	Response assignRole(String email,String rolename);
	
	List<String> getActiveEmailList();

	List<String> getRoleList();

}