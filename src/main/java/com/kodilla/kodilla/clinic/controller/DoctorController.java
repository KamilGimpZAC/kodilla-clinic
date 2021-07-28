package com.kodilla.kodilla.clinic.controller;

import com.kodilla.kodilla.clinic.domain.Doctor;
import com.kodilla.kodilla.clinic.dto.DoctorDto;
import com.kodilla.kodilla.clinic.exception.DoctorException;
import com.kodilla.kodilla.clinic.facade.DoctorFacade;
import com.kodilla.kodilla.clinic.mapper.DoctorMapper;
import com.kodilla.kodilla.clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class DoctorController {

    private final DoctorMapper doctorMapper;
    private final DoctorService doctorService;
    private final DoctorFacade doctorFacade;

    @Autowired
    public DoctorController(DoctorMapper doctorMapper, DoctorService doctorService, DoctorFacade doctorFacade) {
        this.doctorMapper = doctorMapper;
        this.doctorService = doctorService;
        this.doctorFacade = doctorFacade;
    }

    @GetMapping(value = "/doctor")
    public List<DoctorDto> getDoctors(){
        return doctorMapper.mapToDoctorDtoList(doctorService.getAllDoctors());
    }

    @GetMapping(value = "/doctor/{id}")
    public DoctorDto getDoctor(@PathVariable Long id) throws DoctorException{
        return doctorMapper.mapToDoctorDto(doctorService.getDoctor(id).orElseThrow(DoctorException::new));
    }

    @PostMapping(value = "/doctor", consumes = APPLICATION_JSON_VALUE)
    public void createDoctor(@RequestBody DoctorDto doctorDto){
        doctorService.saveDoctor(doctorMapper.mapToDoctor(doctorDto));
    }

    @DeleteMapping(value = "/doctor/{id}")
    public void deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
    }

    @PutMapping(value = "/doctor", consumes = APPLICATION_JSON_VALUE)
    public DoctorDto updateDoctor(@RequestBody DoctorDto doctorDto){
        Long id = doctorDto.getId();
        doctorService.deleteDoctor(id);
        doctorService.saveDoctor(doctorMapper.mapToDoctor(doctorDto));
        return doctorDto;
    }

    @PutMapping(value = "/doctor/{id}/{rating}")
    public int updateRating(@PathVariable Long id, @PathVariable int rating) throws DoctorException {
        Doctor doctor = doctorService.getDoctor(id).orElseThrow(DoctorException::new);
        doctor.setRating(rating);
        doctorService.deleteDoctor(id);
        doctorService.saveDoctor(doctor);
        return doctor.getRating();
    }

    @GetMapping(value = "/doctor/visits")
    public List<DoctorDto> getFreeVisits(){
        List<Doctor> output = doctorService.getAllDoctors();
        output.stream()
                .filter(doctor -> doctor.isFree())
                .collect(Collectors.toList());
        return doctorMapper.mapToDoctorDtoList(output);
    }

    @PutMapping(value = "/doctor/{id}")
    public String bookVisit(@PathVariable Long id) throws DoctorException{
        Doctor doctor = doctorService.getDoctor(id).orElseThrow(DoctorException::new);
        doctor.setFree(false);
        doctorService.deleteDoctor(id);
        doctorService.saveDoctor(doctor);
        return doctorFacade.doctorAdmission(doctor);
    }
}
