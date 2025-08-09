package Groupstudy.example.Groupstudy.Controller;

import Groupstudy.example.Groupstudy.Dto.*;
import Groupstudy.example.Groupstudy.Service.OtpService;
import Groupstudy.example.Groupstudy.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserOtpController {

    private final UserService userService;
    private final OtpService otpService;

    public UserOtpController(UserService userService, OtpService otpService) {
        this.userService = userService;
        this.otpService = otpService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto requestDto) {
        UUID userId = userService.registerUserWithRole(
                requestDto.getUser(),
                requestDto.getCollegeDetails()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully with ID: " + userId);
    }



    // Generate OTP for email
    @PostMapping("/otp/generate")
    public ResponseEntity<String> generateOtp(@RequestBody GenerateOtpRequestDto requestDto) {
        boolean success = otpService.generateOtp(requestDto);
        if (success) {
            return ResponseEntity.ok("OTP sent successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with this email.");
        }
    }

    // Verify OTP for email and code
    @PostMapping("/otp/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtpRequestDto requestDto) {
        boolean verified = otpService.verifyOtp(requestDto);
        if (verified) {
            return ResponseEntity.ok("OTP verified successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired OTP.");
        }
    }
}

