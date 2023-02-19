package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

/**
 * @author kurstan
 * @created at 17.02.2023 12:06
 */
@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_id_gen"
    )
    @SequenceGenerator(
            name = "department_id_gen",
            sequenceName = "department_seq",
            allocationSize = 1
    )
    private Long id;
    private String name;
    @ManyToOne(cascade = {REFRESH, DETACH, MERGE, PERSIST})
    private Hospital hospital;
    @ManyToMany(cascade = {REFRESH, DETACH, MERGE, PERSIST}, fetch = FetchType.EAGER)
    private List<Doctor> doctors;
}
