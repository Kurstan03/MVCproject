package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;
import peaksoft.repository.DoctorRepository;

import java.util.List;

/**
 * @author kurstan
 * @created at 18.02.2023 4:11
 */
@Repository
@Transactional
public class DoctorRepositoryImpl implements DoctorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Doctor> getAll(Long id) {
        return entityManager.createQuery("from Doctor d join d.hospital h where h.id = :id", Doctor.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void save(Long id, Doctor doctor) {
        Hospital hospital = entityManager.createQuery("from Hospital where id = :id", Hospital.class)
                .setParameter("id", id)
                .getSingleResult();
        hospital.addDoctor(doctor);
        doctor.setHospital(hospital);
        entityManager.persist(doctor);
    }

    @Override
    public Doctor findById(Long doctorId) {
        return entityManager.createQuery("from Doctor where id = :id", Doctor.class)
                .setParameter("id", doctorId).getSingleResult();
    }

    @Override
    public void update(Long doctorId, Doctor doctor) {
        entityManager.createQuery("update Doctor set firstName = :f, lastName = :l," +
                        "position  = :p, email = :e where id = :id")
                .setParameter("f", doctor.getFirstName())
                .setParameter("l", doctor.getLastName())
                .setParameter("p", doctor.getPosition())
                .setParameter("e", doctor.getEmail())
                .setParameter("id", doctorId)
                .executeUpdate();
    }

    @Override
    public void delete(Long doctorId) {
        entityManager.remove(
                entityManager.createQuery("from Doctor where id = :id", Doctor.class)
                        .setParameter("id", doctorId).getSingleResult());
    }
}
