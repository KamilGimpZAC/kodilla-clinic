package com.kodilla.kodilla.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PatientDto {
    private Long id;
    private String name;
    private String surname;
    private int peselId;
    private String medicalHistory;
    private List<DoctorDto> doctorDtoList;
}
