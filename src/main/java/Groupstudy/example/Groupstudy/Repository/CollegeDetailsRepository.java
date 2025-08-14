package Groupstudy.example.Groupstudy.Repository;

import Groupstudy.example.Groupstudy.Entity.CollegeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CollegeDetailsRepository extends JpaRepository<CollegeDetails, String> {

    Optional<CollegeDetails> findByUserIdAndIsDeletedFalse(String userId);
}



