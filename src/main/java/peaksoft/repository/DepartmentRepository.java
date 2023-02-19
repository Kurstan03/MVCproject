package peaksoft.repository;

import peaksoft.entity.Department;

import java.util.List;

/**
 * @author kurstan
 * @created at 18.02.2023 11:06
 */
public interface DepartmentRepository {
    List<Department> getAll(Long id);

    void update(Long departmentId, Department department);

    void save(Long hospitalId, Department department);

    void delete(Long departmentId);

    Department findById(Long departmentId);

}
