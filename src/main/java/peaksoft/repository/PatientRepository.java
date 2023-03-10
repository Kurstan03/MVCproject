package peaksoft.repository;

import peaksoft.entity.Patient;

import java.util.List;
import java.util.Optional;

/**
 * @author kurstan
 * @created at 17.02.2023 22:54
 */
public interface PatientRepository {
    List<Patient> getAll(Long hospitalId);

    void save(Long hospitalId, Patient patient);

    Optional<Patient> getById(Long patientId);

    void update(Long patientId, Patient patient);

    void delete(Long patientId);

}
