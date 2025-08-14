package Groupstudy.example.Groupstudy.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {

    @NotBlank(message = "Email or mobile number is required")
    private String emailOrMobile;

    @NotBlank(message = "Password is required")
    private String password;
}

