package peaksoft.repository;

import peaksoft.entity.Appointment;
import peaksoft.entity.Department;
import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;

import java.util.List;
import java.util.Optional;

/**
 * @author kurstan
 * @created at 18.02.2023 4:11
 */
public interface DoctorRepository {
    List<Doctor> getAll(Long id);

    void save(Long id, Doctor doctor);

    Optional<Doctor> findById(Long doctorId);

    void update(Long doctorId, Doctor doctor);

    void delete(Long doctorId, Hospital hospital);

    List<Department> getDepartments(Long doctorId);

    void assignToDepartment(Doctor doctor);

    void deleteDepartment(Doctor doctor);

    List<Appointment> getAppointments(Long doctorId);
}
