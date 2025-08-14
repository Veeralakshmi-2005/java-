package Groupstudy.example.Groupstudy.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class OtpVerifyDto {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String emailId;

    @NotBlank(message = "OTP code is required")
    @Pattern(regexp = "\\d{4}", message = "OTP must be 4 digits")
    private String otpCode;
}

