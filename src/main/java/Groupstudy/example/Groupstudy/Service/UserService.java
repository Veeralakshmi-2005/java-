package Groupstudy.example.Groupstudy.Service;

import Groupstudy.example.Groupstudy.Dto.*;
import Groupstudy.example.Groupstudy.Entity.*;
import Groupstudy.example.Groupstudy.Enum.UserCategory;
import Groupstudy.example.Groupstudy.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final OtpVerificationRepository otpRepository;
    private final CollegeDetailsRepository collegeRepository;

    // ---------- REGISTER ----------
    public void register(RegisterDto dto) {
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmailId());
        user.setMobileNumber(dto.getMobileNumber());
        user.setUserCategory(dto.getUserCategoryName());
        userRepository.save(user);
    }

    // ---------- LOGIN ----------
    public LoginResponseDto login(LoginDto dto) {
        Optional<User> userOpt = dto.getEmailOrMobile().contains("@") ?
                userRepository.findByEmailAndIsDeletedFalse(dto.getEmailOrMobile()) :
                userRepository.findByMobileNumberAndIsDeletedFalse(dto.getEmailOrMobile());

        if (userOpt.isEmpty()) return new LoginResponseDto("User not found", null);

        User user = userOpt.get();

        if (user.getPassword() == null || !user.getPassword().equals(dto.getPassword()))
            return new LoginResponseDto("Incorrect password", null);

        Optional<OtpVerification> otpOpt = otpRepository.findByUserAndVerifiedFalseAndIsDeletedFalse(user);
        if (otpOpt.isPresent() &&
                (user.getUserCategory() == UserCategory.PROFESSOR || user.getUserCategory() == UserCategory.INSTITUTION)) {
            return new LoginResponseDto("User not verified. Please verify OTP.", null);
        }

        return new LoginResponseDto("Login successful", "dummy-token-" + user.getId());
    }

    // ---------- OTP GENERATE (TEST MODE) ----------
    public String generateOtp(String email) {
        User user = userRepository.findByEmailAndIsDeletedFalse(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String otp = String.valueOf((int)(Math.random() * 9000) + 1000);

        OtpVerification otpEntity = new OtpVerification();
        otpEntity.setUser(user);
        otpEntity.setOtp(otp);
        otpEntity.setVerified(false);
        otpRepository.save(otpEntity);

        // -------------------------------
        // For testing only: return OTP in response
        return "OTP generated: " + otp;
    }

    // ---------- OTP VERIFY ----------
    public void verifyOtp(String email, String otpCode) {
        User user = userRepository.findByEmailAndIsDeletedFalse(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        OtpVerification otp = otpRepository.findByUserAndOtpAndIsDeletedFalse(user, otpCode)
                .orElseThrow(() -> new RuntimeException("Invalid OTP"));

        otp.setVerified(true);
        otp.setModified(LocalDateTime.now());
        otpRepository.save(otp);
    }

    // ---------- COLLEGE DETAILS ----------
    public void addOrUpdateCollegeDetails(CollegeDetailsDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        CollegeDetails college;
        if (dto.getCollegeId() != null && !dto.getCollegeId().isEmpty()) {
            college = collegeRepository.findById(dto.getCollegeId()).orElse(new CollegeDetails());
        } else {
            college = new CollegeDetails();
        }

        college.setUser(user);

        // COMMON fields
        college.setCollegeName(dto.getCollegeName());
        college.setLocation(dto.getLocation());
        college.setUniversity(dto.getUniversity());

        // Conditional fields
        switch (user.getUserCategory()) {
            case STUDENT -> {
                college.setTeacherIdentificationNumber(null);
                college.setCollegeRegistrationNo(null);
            }
            case PROFESSOR -> {
                college.setTeacherIdentificationNumber(dto.getTeacherIdentificationNumber());
                college.setCollegeRegistrationNo(null);
            }
            case INSTITUTION -> {
                college.setCollegeRegistrationNo(dto.getCollegeRegistrationNo());
                college.setTeacherIdentificationNumber(null);
            }
        }

        collegeRepository.save(college);
    }

    // ---------- PASSWORD SET AFTER COLLEGE DETAILS ----------
    public void updatePassword(String userId, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new RuntimeException("Password and Confirm Password do not match");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(password);
        user.setModified(LocalDateTime.now());
        userRepository.save(user);
    }
}




















