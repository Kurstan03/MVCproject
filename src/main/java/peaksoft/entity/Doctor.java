package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;


/**
 * @author kurstan
 * @created at 17.02.2023 12:06
 */
@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "doctor_id_gen"
    )
    @SequenceGenerator(
            name = "doctor_id_gen",
            sequenceName = "doctor_seq",
            allocationSize = 1
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private String email;
    @ManyToOne(cascade = {REFRESH, DETACH, MERGE, PERSIST})
    private Hospital hospital;
    @ManyToMany(mappedBy = "doctors", cascade = {REFRESH, DETACH, MERGE, PERSIST})
    private List<Department> departments;
    @OneToMany(mappedBy = "doctor", cascade = ALL, fetch = FetchType.EAGER)
    private List<Appointment> appointments;
    public void addAppointment(Appointment appointment){
        if (appointments == null){
            appointments = new ArrayList<>();
        }
        appointments.add(appointment);
    }
}
