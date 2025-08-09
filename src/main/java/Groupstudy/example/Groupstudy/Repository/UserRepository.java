package Groupstudy.example.Groupstudy.Repository;

import Groupstudy.example.Groupstudy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmailId(String emailId);
}

