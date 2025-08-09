package Groupstudy.example.Groupstudy.Repository;

import Groupstudy.example.Groupstudy.Entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfessorRepository extends JpaRepository<Professor, UUID> {
    Optional<Professor> findByUser_Id(UUID id);
}

