package Groupstudy.example.Groupstudy.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OtpGenerateDto {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String emailId;
}

