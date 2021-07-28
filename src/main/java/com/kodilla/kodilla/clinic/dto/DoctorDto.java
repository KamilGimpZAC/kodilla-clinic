package com.kodilla.kodilla.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DoctorDto {
    private Long id;
    private String name;
    private String surname;
    private String specialization;
    private int price;
    private int rating;
    private String dayOfWeek;
    private LocalDateTime officeHoursStart;
    private LocalDateTime officeHoursEnd;
    private boolean free;
    private List<PatientDto> patientDtoList;
}
