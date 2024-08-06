package com.pranay.happ.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UserRequestIDGenerator {

	
	 public static String generateUserID() {
	        LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
	        String dateString = now.format(formatter);

	        Random random = new Random();
	        int randomNum = random.nextInt(9999)+1000;
	        String userId = dateString+randomNum;
	        return userId;
	 }
	
	
}