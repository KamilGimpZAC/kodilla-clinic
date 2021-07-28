package com.kodilla.kodilla.clinic.service;

import com.kodilla.kodilla.clinic.domain.Patient;
import com.kodilla.kodilla.clinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatient(final Long id){
        return patientRepository.findById(id);
    }

    public void deletePatient(final Long id){
        patientRepository.deleteById(id);
    }

    public Patient savePatient(final Patient patient){
        return patientRepository.save(patient);
    }
}
