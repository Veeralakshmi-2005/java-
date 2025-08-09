package Groupstudy.example.Groupstudy.Repository;

import Groupstudy.example.Groupstudy.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByUser_Id(UUID id);

}

