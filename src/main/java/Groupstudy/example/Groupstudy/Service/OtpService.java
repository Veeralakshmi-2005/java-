package Groupstudy.example.Groupstudy.Service;

import Groupstudy.example.Groupstudy.Dto.GenerateOtpRequestDto;
import Groupstudy.example.Groupstudy.Dto.VerifyOtpRequestDto;
import Groupstudy.example.Groupstudy.Entity.Otp;
import Groupstudy.example.Groupstudy.Entity.User;
import Groupstudy.example.Groupstudy.Repository.OtpRepository;
import Groupstudy.example.Groupstudy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    // Generate a 4-digit OTP, save it, and send to email
    public boolean generateOtp(GenerateOtpRequestDto requestDto) {
        Optional<User> userOpt = userRepository.findByEmailId(requestDto.getEmailId());
        if (userOpt.isEmpty()) {
            return false;  // user not found
        }

        User user = userOpt.get();

        String generatedOtp = String.format("%04d", new Random().nextInt(10000)); // 4 digit OTP

        Otp otp = new Otp();
        otp.setUser(user);
        otp.setOtpCode(generatedOtp);
        otp.setExpiryTime(LocalDateTime.now().plusMinutes(5)); // expires in 5 mins
        otp.setVerified(false);

        otpRepository.save(otp);

        // Send OTP to email
        sendOtpEmail(user.getEmailId(), generatedOtp);

        return true;
    }

    // Verify OTP code
    public boolean verifyOtp(VerifyOtpRequestDto requestDto) {
        Optional<User> userOpt = userRepository.findByEmailId(requestDto.getEmailId());
        if (userOpt.isEmpty()) {
            return false;  // user not found
        }

        User user = userOpt.get();

        Optional<Otp> otpOpt = otpRepository.findTopByUserEmailIdOrderByExpiryTimeDesc(user.getEmailId());

        if (otpOpt.isEmpty()) {
            return false;  // no OTP found for user
        }

        Otp otp = otpOpt.get();

        // Check OTP match and expiry time
        if (otp.getOtpCode().equals(requestDto.getOtpCode()) && otp.getExpiryTime().isAfter(LocalDateTime.now())) {
            otp.setVerified(true);
            otpRepository.save(otp);

            user.setVerified(true);
            userRepository.save(user);

            return true;
        }

        return false;
    }

    // Helper method to send OTP via email
    private void sendOtpEmail(String toEmail, String otpCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otpCode + "\nThis code is valid for 5 minutes.");
        mailSender.send(message);
    }
}







