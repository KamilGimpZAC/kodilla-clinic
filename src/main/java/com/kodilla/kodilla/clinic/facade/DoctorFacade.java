package com.kodilla.kodilla.clinic.facade;

import com.kodilla.kodilla.clinic.domain.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorFacade {

    public String doctorAdmission(final Doctor doctor){
        return doctor.getDayOfWeek() + " " + doctor.getOfficeHoursStart() + " " + doctor.getOfficeHoursEnd();
    }
}
