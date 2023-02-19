package peaksoft.service;

import peaksoft.entity.Doctor;

import javax.print.Doc;
import java.util.List;

/**
 * @author kurstan
 * @created at 18.02.2023 4:09
 */
public interface DoctorService {
    List<Doctor> getAll(Long id);

    void save(Long id, Doctor doctor);

    Doctor findById(Long doctorId);

    void update(Long doctorId, Doctor doctor);

    void delete(Long doctorId);
}
