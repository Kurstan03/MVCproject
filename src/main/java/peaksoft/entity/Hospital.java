package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kurstan
 * @created at 17.02.2023 12:03
 */
@Entity
@Table(name = "hospitals")
@Getter
@Setter
@NoArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hospital_id_gen"
    )
    @SequenceGenerator(
            name = "hospital_id_gen",
            sequenceName = "hospital_seq",
            allocationSize = 1
    )
    private Long id;
    private String name;
    private String address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital")
    private List<Doctor> doctors;

    public void addDoctor(Doctor doctor){
        if (doctors == null){
            doctors = new ArrayList<>();
        }
        doctors.add(doctor);
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital")
    private List<Patient> patients;

    public void addPatient(Patient patient){
        if (patients == null){
            patients = new ArrayList<>();
        }
        patients.add(patient);
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital")
    private List<Department> departments;

    public void addDepartment(Department department){
        if (departments == null){
            departments = new ArrayList<>();
        }
        departments.add(department);
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Appointment> appointments;

    public void addAppointment(Appointment appointment){
        if (appointments == null){
            appointments = new ArrayList<>();
        }
        appointments.add(appointment);
    }

}
