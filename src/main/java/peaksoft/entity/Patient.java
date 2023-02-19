package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Gender;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

/**
 * @author kurstan
 * @created at 17.02.2023 12:06
 */
@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_id_gen"
    )
    @SequenceGenerator(
            name = "patient_id_gen",
            sequenceName = "patient_seq",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private Gender gender;
    private String email;

    @ManyToOne(cascade = {REFRESH, DETACH, MERGE, PERSIST})
    private Hospital hospital;

    @OneToMany(mappedBy = "patient", cascade = ALL, fetch = FetchType.EAGER)
    private List<Appointment> appointments;
    public void addAppointment(Appointment appointment){
        if (appointments == null){
            appointments = new ArrayList<>();
        }
        appointments.add(appointment);
    }
}
