package Groupstudy.example.Groupstudy.Repository;

import Groupstudy.example.Groupstudy.Entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InstitutionRepository extends JpaRepository<Institution, UUID> {
    Optional<Institution> findByUser_Id(UUID id);
}

