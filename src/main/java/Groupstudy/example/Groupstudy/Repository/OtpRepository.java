package Groupstudy.example.Groupstudy.Repository;

import Groupstudy.example.Groupstudy.Entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OtpRepository extends JpaRepository<Otp, UUID> {
    Optional<Otp> findTopByUserEmailIdOrderByExpiryTimeDesc(String emailId);
}



