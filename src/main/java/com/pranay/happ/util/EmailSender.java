package com.pranay.happ.util;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.pranay.happ.entity.Appointment;

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
                    "    <title>Account Creation Confirmation</title>\r\n" +
                    "    <style>\r\n" +
                    "        body { background-color: #f4f4f4; font-family: Arial, sans-serif; color: #333; }\r\n" +
                    "        .container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #ffffff; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }\r\n" +
                    "        .header { background-color: #007bff; color: #ffffff; padding: 15px; text-align: center; border-radius: 8px 8px 0 0; }\r\n" +
                    "        .header h1 { margin: 0; font-size: 24px; }\r\n" +
                    "        .content { padding: 20px; background-color: #f8f9fa; }\r\n" +
                    "        .content p { line-height: 1.6; margin-bottom: 15px; }\r\n" +
                    "        .content strong { color: #007bff; }\r\n" +
                    "        .footer { margin-top: 20px; text-align: center; font-size: 12px; color: #999999; }\r\n" +
                    "    </style>\r\n" +
                    "</head>\r\n" +
                    "<body>\r\n" +
                    "    <div class=\"container\">\r\n" +
                    "        <div class=\"header\">\r\n" +
                    "            <h1>Welcome, " + firstName + " " + lastName + "!</h1>\r\n" +
                    "        </div>\r\n" +
                    "        <div class=\"content\">\r\n" +
                    "            <p>We are pleased to inform you that your account has been successfully created with the Hospital Management System.</p>\r\n" +
                    "            <p>Your account number is: <strong>" + userNumber + "</strong></p>\r\n" +
                    "            <p>We are committed to providing you with the highest quality of service and ensuring that your experience with us is seamless.</p>\r\n" +
                    "            <p>If you have any questions or require further assistance, please do not hesitate to reach out to our support team.</p>\r\n" +
                    "            <p>We look forward to serving you.</p>\r\n" +
                    "            <p>Best regards,</p>\r\n" +
                    "            <p>The Hospital Management System Team</p>\r\n" +
                    "        </div>\r\n" +
                    "        <div class=\"footer\">\r\n" +
                    "            <p>&copy; 2024 Hospital Management System. All rights reserved.</p>\r\n" +
                    "        </div>\r\n" +
                    "    </div>\r\n" +
                    "</body>\r\n" +
                    "</html>", true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendAppointmentConfirmationEmail(JavaMailSender javaMailSender, Appointment appointment) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(appointment.getEmail());
            helper.setSubject("Appointment Confirmation - Hospital Management System");

            String content = "<!DOCTYPE html>\r\n" +
                    "<html lang=\"en\">\r\n" +
                    "<head>\r\n" +
                    "    <meta charset=\"UTF-8\">\r\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" +
                    "    <title>Appointment Confirmation</title>\r\n" +
                    "    <style>\r\n" +
                    "        body { background-color: #f4f4f4; font-family: Arial, sans-serif; color: #333; }\r\n" +
                    "        .container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #ffffff; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }\r\n" +
                    "        .header { background-color: #007bff; color: #ffffff; padding: 15px; text-align: center; border-radius: 8px 8px 0 0; }\r\n" +
                    "        .header h1 { margin: 0; font-size: 24px; }\r\n" +
                    "        .content { padding: 20px; background-color: #f8f9fa; }\r\n" +
                    "        .content p { line-height: 1.6; margin-bottom: 15px; }\r\n" +
                    "        .content strong { color: #007bff; }\r\n" +
                    "        .footer { margin-top: 20px; text-align: center; font-size: 12px; color: #999999; }\r\n" +
                    "    </style>\r\n" +
                    "</head>\r\n" +
                    "<body>\r\n" +
                    "    <div class=\"container\">\r\n" +
                    "        <div class=\"header\">\r\n" +
                    "            <h1>Appointment Confirmation</h1>\r\n" +
                    "        </div>\r\n" +
                    "        <div class=\"content\">\r\n" +
                    "            <p>Dear " + appointment.getPname() + ",</p>\r\n" +
                    "            <p>Your appointment has been successfully booked. Here are the details:</p>\r\n" +
                    "            <p><strong>Appointment Number:</strong> " + appointment.getAppointmentNumber() + "</p>\r\n" +
                    "            <p><strong>Patient Name:</strong> " + appointment.getPname() + "</p>\r\n" +
                    "            <p><strong>Date:</strong> " + appointment.getDate() + "</p>\r\n" +
                    "            <p><strong>Time:</strong> " + appointment.getTime() + "</p>\r\n" +
                    "            <p><strong>Appointed Doctor:</strong> " + appointment.getAppointedDoctor() + "</p>\r\n" +
                    "            <p><strong>Location:</strong> " + appointment.getLocation() + "</p>\r\n" +
                    "            <p><strong>Category:</strong> " + appointment.getCategory() + "</p>\r\n" +
                    "            <p><strong>Visit Type:</strong> " + appointment.getVisitType() + "</p>\r\n" +
                    "            <p>If you have any questions or need to reschedule, please contact our support team.</p>\r\n" +
                    "            <p>Thank you for choosing our services. We look forward to your visit.</p>\r\n" +
                    "            <p>Best regards,</p>\r\n" +
                    "            <p>The Hospital Management System Team</p>\r\n" +
                    "        </div>\r\n" +
                    "        <div class=\"footer\">\r\n" +
                    "            <p>&copy; 2024 Hospital Management System. All rights reserved.</p>\r\n" +
                    "        </div>\r\n" +
                    "    </div>\r\n" +
                    "</body>\r\n" +
                    "</html>";

            helper.setText(content, true);
            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
