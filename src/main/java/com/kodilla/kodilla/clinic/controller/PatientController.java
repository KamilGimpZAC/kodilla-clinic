package com.kodilla.kodilla.clinic.controller;

import com.kodilla.kodilla.clinic.domain.Patient;
import com.kodilla.kodilla.clinic.dto.DoctorDto;
import com.kodilla.kodilla.clinic.dto.PatientDto;
import com.kodilla.kodilla.clinic.exception.PatientException;
import com.kodilla.kodilla.clinic.mapper.PatientMapper;
import com.kodilla.kodilla.clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping(name = "/v1")
public class PatientController {
    private final PatientMapper patientMapper;
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientMapper patientMapper, PatientService patientService) {
        this.patientMapper = patientMapper;
        this.patientService = patientService;
    }

    @PostMapping(value = "/patient", consumes = APPLICATION_JSON_VALUE)
    public void createPatient(@RequestBody PatientDto patientDto){
        patientService.savePatient(patientMapper.mapToPatient(patientDto));
    }

    @GetMapping(value = "/patient/{id}")
    public PatientDto getPatient(@PathVariable Long id) throws PatientException {
        return patientMapper.mapToPatientDto(patientService.getPatient(id).orElseThrow(PatientException::new));
    }

    @GetMapping(value = "/patient")
    public List<PatientDto> getPatients(){
        return patientMapper.mapToPatientDtoList(patientService.getPatients());
    }

    @PutMapping(value = "/patient", consumes = APPLICATION_JSON_VALUE)
    public PatientDto updatePatient(@RequestBody PatientDto patientDto){
        Long id = patientDto.getId();
        patientService.deletePatient(id);
        patientService.savePatient(patientMapper.mapToPatient(patientDto));
        return patientDto;
    }

    @DeleteMapping(value = "/patient/{id}")
    public void deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
    }

    @GetMapping(value = "/patient/my_doctors/{id}")
    public List<DoctorDto> getMyDoctors(@PathVariable Long id) throws PatientException{
        Patient patient = patientService.getPatient(id).orElseThrow(PatientException::new);
        PatientDto patientDto = patientMapper.mapToPatientDto(patient);
        return patientDto.getDoctorDtoList();
    }

}
