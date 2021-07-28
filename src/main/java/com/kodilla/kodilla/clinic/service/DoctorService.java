package com.kodilla.kodilla.clinic.service;

import com.kodilla.kodilla.clinic.domain.Doctor;
import com.kodilla.kodilla.clinic.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctor(final Long id){
        return doctorRepository.findById(id);
    }

    public void deleteDoctor(final Long id){
        doctorRepository.deleteById(id);
    }

    public Doctor saveDoctor(final Doctor doctor){
        return doctorRepository.save(doctor);
    }
}
