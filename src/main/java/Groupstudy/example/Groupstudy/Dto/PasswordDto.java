package Groupstudy.example.Groupstudy.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordDto {

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password should be at least 8 characters")
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;
}

