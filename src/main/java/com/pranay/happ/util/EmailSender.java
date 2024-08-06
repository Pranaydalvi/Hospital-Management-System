package com.pranay.happ.util;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailSender {

	public static void sendEmail(JavaMailSender javaMailSender, String to, String firstName, String lastName, String userNumber) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("Thanks For Creating Account.");
            helper.setText("<!DOCTYPE html>\r\n" +
                    "<html lang=\"en\">\r\n" +
                    "<head>\r\n" +
                    "    <meta charset=\"UTF-8\">\r\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" +
                    "    <title>Welcome to Our Service</title>\r\n" +
                    "    <style>\r\n" +
                    "        body { background-color: whitesmoke; font-family: Arial, sans-serif; color: #333; }\r\n" +
                    "        .container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #fff; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }\r\n" +
                    "        .header { background-color: #007bff; color: #fff; padding: 10px 0; text-align: center; border-radius: 10px 10px 0 0; }\r\n" +
                    "        .header h1 { margin: 0; font-size: 24px; }\r\n" +
                    "        .content { padding: 20px;background-color: aquamarine; }\r\n" +
                    "        .content p { line-height: 1.6; }\r\n" +
                    "        .content strong { color: #007bff; }\r\n" +
                    "        .footer { margin-top: 20px; text-align: center; font-size: 12px; color: #999; }\r\n" +
                    "    </style>\r\n" +
                    "</head>\r\n" +
                    "<body>\r\n" +
                    "    <div class=\"container\">\r\n" +
                    "        <div class=\"header\">\r\n" +
                    "            <h1>Welcome, " + firstName + " " + lastName + "!</h1>\r\n" +
                    "        </div>\r\n" +
                    "        <div class=\"content\">\r\n" +
                    "            <p>Thank you for creating an account with us. We are thrilled to have you on board!</p>\r\n" +
                    "            <p>Your account number is: <strong>" + userNumber + "</strong></p>\r\n" +
                    "            <p>We look forward to serving you and providing the best experience possible.</p>\r\n" +
                    "            <p>If you have any questions or need assistance, please feel free to contact our support team.</p>\r\n" +
                    "            <p>Best regards,</p>\r\n" +
                    "            <p>The Team</p>\r\n" +
                    "        </div>\r\n" +
                    "        <div class=\"footer\">\r\n" +
                    "            <p>&copy; 2024 Hospital Mgt Sys. All rights reserved.</p>\r\n" +
                    "        </div>\r\n" +
                    "    </div>\r\n" +
                    "</body>\r\n" +
                    "</html>", true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
