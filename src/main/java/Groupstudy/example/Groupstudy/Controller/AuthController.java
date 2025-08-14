package Groupstudy.example.Groupstudy.Controller;

import Groupstudy.example.Groupstudy.Dto.*;
import Groupstudy.example.Groupstudy.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // 1. SignUp → basic info
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto dto) {
        userService.register(dto);
        return ResponseEntity.ok("Registered successfully");
    }

    // 2. OTP Generate
    @PostMapping("/otp/generate")
    public ResponseEntity<String> generateOtp(@Valid @RequestBody OtpGenerateDto dto) {
        return ResponseEntity.ok(userService.generateOtp(dto.getEmailId()));
    }

    // 3. OTP Verify
    @PostMapping("/otp/verify")
    public ResponseEntity<String> verifyOtp(@Valid @RequestBody OtpVerifyDto dto) {
        userService.verifyOtp(dto.getEmailId(), dto.getOtpCode());
        return ResponseEntity.ok("OTP verified successfully");
    }

    // 4. College Details → after OTP verified
    @PostMapping("/college")
    public ResponseEntity<String> addOrUpdateCollege(@Valid @RequestBody CollegeDetailsDto dto) {
        userService.addOrUpdateCollegeDetails(dto);
        return ResponseEntity.ok("College details updated successfully");
    }

    // 5. Password Update → after CollegeDetails
    @PostMapping("/users/{id}/password")
    public ResponseEntity<String> updatePassword(@PathVariable String id,
                                                 @Valid @RequestBody PasswordDto dto) {
        userService.updatePassword(id, dto.getPassword(), dto.getConfirmPassword());
        return ResponseEntity.ok("Your password updated successfully");
    }
}
