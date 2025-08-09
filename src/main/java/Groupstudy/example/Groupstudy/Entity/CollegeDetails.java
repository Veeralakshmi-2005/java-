package Groupstudy.example.Groupstudy.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollegeDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID collegeId;

    private String collegeName;
    private String location;
    private String university;

    // Optional fields depending on role
    private String teacherIdentificationNumber;  // for Professors only
    private String collegeRegistrationNo;        // for Institutions only

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}




