package peaksoft.service.impl;

import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.springframework.stereotype.Service;
import peaksoft.entity.Department;
import peaksoft.repository.DepartmentRepository;
import peaksoft.service.DepartmentService;

import java.util.List;

/**
 * @author kurstan
 * @created at 18.02.2023 11:05
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAll(Long id) {
        return departmentRepository.getAll(id);
    }

    @Override
    public void update(Long departmentId, Department department) {
        departmentRepository.update(departmentId, department);
    }

    @Override
    public void save(Long hospitalId, Department department) {
        departmentRepository.save(hospitalId, department);
    }

    @Override
    public void delete(Long departmentId) {
        departmentRepository.delete(departmentId);
    }

    @Override
    public Department findById(Long departmentId) {
        return departmentRepository.findById(departmentId);
    }
}
