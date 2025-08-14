package Groupstudy.example.Groupstudy.Repository;

import Groupstudy.example.Groupstudy.Entity.OtpVerification;
import Groupstudy.example.Groupstudy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OtpVerificationRepository extends JpaRepository<OtpVerification, String> {

    Optional<OtpVerification> findByUserAndVerifiedFalseAndIsDeletedFalse(User user);

    Optional<OtpVerification> findByUserAndOtpAndIsDeletedFalse(User user, String otp);
}









