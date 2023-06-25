package com.example.VaccinationBookingSystem.service.impl;

import com.example.VaccinationBookingSystem.DTO.RequestDto.AppointmentRequestDTO;
import com.example.VaccinationBookingSystem.DTO.ResponseDto.AppointmentResponseDTO;
import com.example.VaccinationBookingSystem.Enum.DoseNo;
import com.example.VaccinationBookingSystem.Exception.DoctorNotFound;
import com.example.VaccinationBookingSystem.Exception.DoseAlreadytaken;
import com.example.VaccinationBookingSystem.Exception.NotElegibleForDose2;
import com.example.VaccinationBookingSystem.Exception.UserNotFound;
import com.example.VaccinationBookingSystem.Model.*;
import com.example.VaccinationBookingSystem.Transfomer.AppintmentTransformer;
import com.example.VaccinationBookingSystem.repository.DoctorRepository;
import com.example.VaccinationBookingSystem.repository.UserReposiory;
import com.example.VaccinationBookingSystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    // send mail
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    UserReposiory userReposiory;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired Dose1ServiceImpl dose1Service;

    @Autowired Dose2ServiceImpl dose2Service;

    @Override
    public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO appointmentRequestDTO) throws UserNotFound, DoctorNotFound, NotElegibleForDose2, DoseAlreadytaken {
        // check  for user

        Optional<User>  optionalUser = userReposiory.findById(appointmentRequestDTO.getUserId());
        if(!optionalUser.isPresent()){
            throw new UserNotFound("User Not Found!!");
        }

        // check for doctor
        Optional<Doctor> optionalDoctor = doctorRepository.findById(appointmentRequestDTO.getDoctorId());
        if(!optionalDoctor.isPresent()){
            throw new DoctorNotFound("Doctor Not Found!!");
        }

        User user = optionalUser.get();
        Doctor doctor = optionalDoctor.get();

        if(user.isDose1Taken() && appointmentRequestDTO.getDoseNo() == DoseNo.DOSE1){
            throw new DoseAlreadytaken("Dose 1 already taken!!");
        }

        if(user.isDose2taken() && appointmentRequestDTO.getDoseNo() == DoseNo.DOSE2){
            throw new DoseAlreadytaken("Dose 2 already taken!!");
        }

        if(appointmentRequestDTO.getDoseNo() == DoseNo.DOSE1){
            Dose1 dose1 = dose1Service.createDose1(user,appointmentRequestDTO.getVaccinationType());
            user.setDose1Taken(true);
            user.setDose1(dose1);
        }
        else{
            if(!user.isDose1Taken()){
                throw new NotElegibleForDose2("Dose1 should taken first!!");
            }
            Dose2 dose2 = dose2Service.createDose2(user,appointmentRequestDTO.getVaccinationType());
            user.setDose2taken(true);
            user.setDose2(dose2);
        }

        Appointment appointment = Appointment.builder()
                .appintmentNo(String.valueOf(UUID.randomUUID()))
                .user(user)
                .doctor(doctor)
                .vaccinationCentre(doctor.getVaccinationCentre())
                .doseNo(appointmentRequestDTO.getDoseNo())
                .build();

        doctor.getAppointments().add(appointment);
        user.getAppointment().add(appointment);

        User savedUser = userReposiory.save(user); // this will save dose1/dose2 and appointments

        Appointment savedAppointment = savedUser.getAppointment().get(savedUser.getAppointment().size()-1);
        doctor.getAppointments().add(savedAppointment);
        doctorRepository.save(doctor);

        // send mail

        String text = "Congrats!! " + user.getName() + " you have successfully booked your " + appointment.getDoseNo() +"\n" +"\n"
                +"User Name : " + user.getName() + "\n" +"\n"
                +"Appointment Number : " + savedAppointment.getAppintmentNo() + "\n" +"\n"
                +"Date of Appointment : " + savedAppointment.getDateOfAppointment() + "\n"+"\n"
                +"Dose No : " + savedAppointment.getDoseNo() + "\n"+"\n"
                +"Vaccination Type : " + appointmentRequestDTO.getVaccinationType() + "\n"+"\n"
                +"Vaccination Center Name : " + savedAppointment.getVaccinationCentre().getName() + "\n"+"\n"
                +"Location : " + savedAppointment.getVaccinationCentre().getLocation()+ "\n"+"\n"
                +"Doctor Name : " + savedAppointment.getDoctor().getName();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("puublicstaticvoidmain@gmail.com");
        message.setTo(user.getEmailId());
        message.setSubject("Appointment Booked Successfully!");
        message.setText(text);
        javaMailSender.send(message);

        return AppintmentTransformer.appointmentToappointmentResponseDTO(appointment,appointmentRequestDTO.getVaccinationType());



    }
}
