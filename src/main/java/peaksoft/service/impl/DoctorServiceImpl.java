package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Doctor;
import peaksoft.repository.DoctorRepository;
import peaksoft.service.DoctorService;

import java.util.List;

/**
 * @author kurstan
 * @created at 18.02.2023 4:09
 */
@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> getAll(Long id) {
        return doctorRepository.getAll(id);
    }

    @Override
    public void save(Long id, Doctor doctor) {
        doctorRepository.save(id, doctor);
    }

    @Override
    public Doctor findById(Long doctorId) {
        return doctorRepository.findById(doctorId);
    }

    @Override
    public void update(Long doctorId, Doctor doctor) {
        doctorRepository.update(doctorId, doctor);
    }

    @Override
    public void delete(Long doctorId) {
        doctorRepository.delete(doctorId);
    }
}
