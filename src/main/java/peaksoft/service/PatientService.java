package peaksoft.service;

import peaksoft.entity.Patient;

import java.util.List;

/**
 * @author kurstan
 * @created at 17.02.2023 22:52
 */
public interface PatientService {
    List<Patient> getAll(Long patientId);
    void save(Long hospitalId, Patient patient);

    Patient getById(Long patientId);

    void update(Long patientId, Patient patient);

    void delete(Long patientId);
}
