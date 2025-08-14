package Groupstudy.example.Groupstudy.Entity;

import Groupstudy.example.Groupstudy.Enum.UserCategory;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String mobileNumber;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserCategory userCategory;

    private boolean isDeleted = false;

    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime modified = LocalDateTime.now();
}









