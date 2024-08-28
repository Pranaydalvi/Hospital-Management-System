package com.pranay.happ.serviceIMPL;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pranay.happ.constant.Constants;
import com.pranay.happ.dto.Response;
import com.pranay.happ.entity.Appointment;
import com.pranay.happ.entity.AssignedDoctor;
import com.pranay.happ.entity.UserRequest;
import com.pranay.happ.repo.AppointmentRepository;
import com.pranay.happ.repo.DoctorRepository;
import com.pranay.happ.repo.UserRepository;
import com.pranay.happ.serviceI.AppointmentServiceI;
import com.pranay.happ.serviceI.PdfGenerateService;
import com.pranay.happ.util.UserRequestIDGenerator;

@Service
public class AppointmentServiceIMPL implements AppointmentServiceI {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PdfGenerateService pdfGenerateService;
    
    @Value("${pdf.directory}")
    private String pdfDirectory;

    @Override
    public Response bookAppointment(Appointment appointment, String usernumber) {
        Response response = new Response();

        try {
            UserRequest user = userRepository.findByUsernumber(usernumber);
            if (user == null) {
                response.setMsg("User not found.");
                return response;
            }
            appointment.setUserRequest(user);

            AssignedDoctor assignedDoctor = doctorRepository.findByCatogoryAndName(appointment.getCategory(), appointment.getAppointedDoctor());
            if (assignedDoctor == null) {
                response.setMsg("Assigned doctor not found.");
                return response;
            }

            long count = appointmentRepository.isDoctorBooked(assignedDoctor.getDoctornumber(), appointment.getDate(), appointment.getTime());
            if (count > 0) {
                appointment.setStatus(Constants.PENDING);
            } else {
                appointment.setStatus(Constants.NEW);
            }
            appointment.setDoctornumber(assignedDoctor.getDoctornumber());

            String apponum = UserRequestIDGenerator.generateUserID();
            appointment.setAppointmentNumber(apponum);

            Appointment savedAppointment = appointmentRepository.save(appointment);
            if (savedAppointment != null) {
                response.setMsg("Appointment Data inserted with status: " + appointment.getStatus());

                // Generate PDF
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("appointmentNumber", appointment.getAppointmentNumber());
                dataMap.put("pname", appointment.getPname());
                dataMap.put("date", appointment.getDate());
                dataMap.put("time", appointment.getTime());
                dataMap.put("appointedDoctor", appointment.getAppointedDoctor());
                dataMap.put("location", appointment.getLocation());
                dataMap.put("category", appointment.getCategory());
                dataMap.put("status", appointment.getStatus());
                dataMap.put("visitType", appointment.getVisitType());
                
                String pdfFileName = "Appointment_" + appointment.getAppointmentNumber() + ".pdf";
                pdfGenerateService.generatePdfFile("appointment", dataMap, pdfFileName);

                response.setMsg("PDF generated successfully: " + pdfFileName);
            } else {
                response.setMsg("Appointment Data not inserted.");
            }
        } catch (Exception e) {
            response.setMsg("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

}
