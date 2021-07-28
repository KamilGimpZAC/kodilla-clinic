package com.kodilla.kodilla.clinic.mapper;

import com.kodilla.kodilla.clinic.domain.Doctor;
import com.kodilla.kodilla.clinic.dto.DoctorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorMapper {
    @Autowired
    private PatientMapper patientMapper;

    public Doctor mapToDoctor(final DoctorDto doctorDto){
        return new Doctor(
                doctorDto.getId(),
                doctorDto.getName(),
                doctorDto.getSurname(),
                doctorDto.getSpecialization(),
                doctorDto.getPrice(),
                doctorDto.getRating(),
                doctorDto.getDayOfWeek(),
                doctorDto.getOfficeHoursStart(),
                doctorDto.getOfficeHoursEnd(),
                doctorDto.isFree(),
                patientMapper.mapToPatientList(doctorDto.getPatientDtoList())
        );
    }

    public DoctorDto mapToDoctorDto(final Doctor doctor){
        return new DoctorDto(
                doctor.getId(),
                doctor.getName(),
                doctor.getSurname(),
                doctor.getSpecialization(),
                doctor.getPrice(),
                doctor.getRating(),
                doctor.getDayOfWeek(),
                doctor.getOfficeHoursStart(),
                doctor.getOfficeHoursEnd(),
                doctor.isFree(),
                patientMapper.mapToPatientDtoList(doctor.getPatients())
        );
    }

    public List<Doctor> mapToDoctorList(final List<DoctorDto> doctorDtoList){
        return doctorDtoList.stream()
                .map(this::mapToDoctor)
                .collect(Collectors.toList());
    }

    public List<DoctorDto> mapToDoctorDtoList(final List<Doctor> doctorList){
        return doctorList.stream()
                .map(this::mapToDoctorDto)
                .collect(Collectors.toList());
    }
}
