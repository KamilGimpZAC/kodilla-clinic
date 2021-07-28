package com.kodilla.kodilla.clinic.mapper;

import com.kodilla.kodilla.clinic.domain.Patient;
import com.kodilla.kodilla.clinic.dto.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientMapper {
    @Autowired
    private DoctorMapper doctorMapper;

    public Patient mapToPatient(final PatientDto patientDto){
        return new Patient(
                patientDto.getId(),
                patientDto.getName(),
                patientDto.getSurname(),
                patientDto.getPeselId(),
                patientDto.getMedicalHistory(),
                doctorMapper.mapToDoctorList(patientDto.getDoctorDtoList())
        );
    }

    public PatientDto mapToPatientDto(final Patient patient){
        return new PatientDto(
                patient.getId(),
                patient.getName(),
                patient.getSurname(),
                patient.getPeselId(),
                patient.getMedicalHistory(),
                doctorMapper.mapToDoctorDtoList(patient.getDoctors())
        );
    }

    public List<Patient> mapToPatientList(final List<PatientDto> patientDtoList){
        return patientDtoList.stream()
                .map(this::mapToPatient)
                .collect(Collectors.toList());
    }

    public List<PatientDto> mapToPatientDtoList(final List<Patient> patientList){
        return patientList.stream()
                .map(this::mapToPatientDto)
                .collect(Collectors.toList());
    }
}
