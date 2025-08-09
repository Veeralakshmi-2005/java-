package Groupstudy.example.Groupstudy.Repository;

import Groupstudy.example.Groupstudy.Entity.CollegeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CollegeDetailsRepository extends JpaRepository<CollegeDetails, UUID> {
    Optional<CollegeDetails> findById(UUID collegeId);
}

