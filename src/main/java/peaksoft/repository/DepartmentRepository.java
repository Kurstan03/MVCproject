package peaksoft.repository;

import peaksoft.entity.Department;
import peaksoft.entity.Doctor;

import java.util.List;
import java.util.Optional;

/**
 * @author kurstan
 * @created at 18.02.2023 11:06
 */
public interface DepartmentRepository {
    List<Department> getAll(Long id);

    void update(Long departmentId, Department department);

    void save(Long hospitalId, Department department);

    void delete(Long departmentId);

    Optional<Department> findById(Long departmentId);

    List<Doctor> getDoctors(Long departmentId);
}
