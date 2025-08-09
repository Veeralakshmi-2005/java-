package Groupstudy.example.Groupstudy.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class VerifyOtpRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String emailId;

    @NotBlank(message = "OTP code is required")
    @Pattern(regexp = "\\d{4}", message = "OTP must be exactly 4 digits")
    private String otpCode;
}


