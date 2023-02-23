package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.entity.Appointment;
import peaksoft.entity.Department;
import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;
import peaksoft.exeptions.NotFoundException;
import peaksoft.repository.AppointmentRepository;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.DoctorRepository;
import peaksoft.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kurstan
 * @created at 18.02.2023 11:05
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.departmentRepository = departmentRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
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
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(
                        ()-> new NotFoundException("Department by id " + departmentId + " not found"));
//        List<Doctor> doctors = department.getDoctors();

//        for (Doctor doctor : doctors) {
//            for (Department doctorDepartment : doctor.getDepartments()) {
//                if (doctorDepartment.getId().equals(departmentId)) {
//                    doctorDepartment.setDoctors(null);
//                }
//            }
//        }
//        for (int i = 0; i < doctors.size(); i++) {
//            doctors.get(i).getDepartments().remove(department);
//        }

        Hospital hospital = department.getHospital();
        List<Appointment> appointments = appointmentRepository.getAll(hospital.getId());
        List<Appointment> depApp = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDepartment().getId().equals(departmentId)) {
                depApp.add(appointment);
            }
        }
        depApp.forEach(a-> a.getDoctor().setAppointments(null));
        depApp.forEach(a-> a.getPatient().setAppointments(null));
        hospital.getAppointments().removeAll(depApp);

        for (int i = 0; i < depApp.size(); i++) {
            appointmentRepository.delete(depApp.get(i).getId());
        }
        departmentRepository.delete(departmentId);
    }

    @Override
    public Department findById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(
                        ()-> new NotFoundException("Department by id " + departmentId + " not found"));
    }

    @Override
    public List<Doctor> getDoctors(Long id, Long departmentId) {
        return departmentRepository.getDoctors(departmentId);
    }
}
