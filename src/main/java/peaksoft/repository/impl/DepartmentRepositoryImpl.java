package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Department;
import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;
import peaksoft.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author kurstan
 * @created at 18.02.2023 11:06
 */
@Repository
@Transactional
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Department> getAll(Long id) {
        return entityManager
                .createQuery("from Department d join d.hospital h where h.id = :id",
                        Department.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void update(Long departmentId, Department department) {
        entityManager.createQuery("update Department set name = :n where id = :id")
                .setParameter("n", department.getName())
                .setParameter("id", departmentId)
                .executeUpdate();
    }

    @Override
    public void save(Long hospitalId, Department department) {
        Hospital hospital = entityManager
                .createQuery("from Hospital where id = :id", Hospital.class)
                .setParameter("id", hospitalId)
                .getSingleResult();
        hospital.addDepartment(department);
        department.setHospital(hospital);
        entityManager.merge(department);
    }

    @Override
    public void delete(Long departmentId) {
//        entityManager.remove(
//                entityManager.createQuery("from Department where id = :id", Department.class)
//                        .setParameter("id", departmentId).getSingleResult()
//        );
        entityManager.createQuery("delete from Department where id = :id")
                .setParameter("id", departmentId)
                .executeUpdate();
    }

    @Override
    public Optional<Department> findById(Long departmentId) {
//        return entityManager.createQuery("from Department where id = :id", Department.class)
//                .setParameter("id", departmentId)
//                .getSingleResult();
        Department department = entityManager.find(Department.class, departmentId);
        return Optional.ofNullable(department);
    }

    @Override
    public List<Doctor> getDoctors(Long departmentId) {
        return entityManager
                .createQuery("from Doctor doc join doc.departments dep where dep.id = :id",
                        Doctor.class).setParameter("id", departmentId).getResultList();
    }
}
