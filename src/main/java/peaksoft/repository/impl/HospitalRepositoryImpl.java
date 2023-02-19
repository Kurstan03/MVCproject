package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Hospital;
import peaksoft.repository.HospitalRepository;

import java.util.List;

/**
 * @author kurstan
 * @created at 17.02.2023 12:41
 */
@Repository
@Transactional
public class HospitalRepositoryImpl implements HospitalRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Hospital> getAllHospitals() {
        return entityManager.createQuery("from Hospital ", Hospital.class).getResultList();
    }
    @Override
    public void save(Hospital hospital) {
        entityManager.persist(hospital);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager
                .createQuery("from Hospital where id = :id", Hospital.class)
                .setParameter("id", id).getSingleResult()
        );
    }

    @Override
    public Hospital getById(Long id) {
        return entityManager
                .createQuery("from Hospital where id = :id", Hospital.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void update(Long id, Hospital hospital) {
        entityManager.createQuery("update Hospital set name = :n, address = :a where id = :id")
                .setParameter("n", hospital.getName())
                .setParameter("a", hospital.getAddress())
                .setParameter("id", id)
                .executeUpdate();
    }
}
