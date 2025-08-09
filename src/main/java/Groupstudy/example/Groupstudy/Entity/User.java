package Groupstudy.example.Groupstudy.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String fullName;
    private String emailId;
    private String mobileNumber;
    private String password;


    private String role;

    private boolean verified;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Otp otp;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Professor professor;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Institution institution;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CollegeDetails collegeDetails;
}





