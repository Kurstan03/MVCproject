package peaksoft.service.impl;

import org.springframework.stereotype.Service;
import peaksoft.entity.Patient;
import peaksoft.repository.PatientRepository;
import peaksoft.service.PatientService;

import java.util.List;

/**
 * @author kurstan
 * @created at 17.02.2023 22:54
 */
@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAll(Long patientId) {
        return patientRepository.getAll(patientId);
    }

    @Override
    public void save(Long hospitalId, Patient patient) {
        patientRepository.save(hospitalId, patient);
    }

    @Override
    public Patient getById(Long patientId) {
        return patientRepository.getById(patientId);
    }

    @Override
    public void update(Long patientId, Patient patient) {
        patientRepository.update(patientId, patient);
    }

    @Override
    public void delete(Long patientId) {
        patientRepository.delete(patientId);
    }
}
