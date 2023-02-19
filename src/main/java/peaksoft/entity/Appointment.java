package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;

/**
 * @author kurstan
 * @created at 17.02.2023 12:06
 */
@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appointment_id_gen"
    )
    @SequenceGenerator(
            name = "appointment_id_gen",
            sequenceName = "appointment_seq",
            allocationSize = 1
    )
    private Long id;
    private LocalDate date;
    @ManyToOne(cascade = {REFRESH, DETACH, MERGE, PERSIST}, fetch = FetchType.EAGER)
    private Patient patient;
    @ManyToOne(cascade = {REFRESH, DETACH, MERGE, PERSIST}, fetch = FetchType.EAGER)
    private Doctor doctor;
    @ManyToOne(cascade = {REFRESH, DETACH, MERGE, PERSIST}, fetch = FetchType.EAGER)
    private Department department;
    @Transient
    private Long patientId;
    @Transient
    private Long doctorId;
    @Transient
    private Long departmentId;
}
