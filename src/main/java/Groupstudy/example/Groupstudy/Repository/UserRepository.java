package Groupstudy.example.Groupstudy.Repository;

import Groupstudy.example.Groupstudy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmailAndIsDeletedFalse(String email);

    Optional<User> findByMobileNumberAndIsDeletedFalse(String mobileNumber);
}





