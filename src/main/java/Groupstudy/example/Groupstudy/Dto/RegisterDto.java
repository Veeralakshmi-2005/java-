package Groupstudy.example.Groupstudy.Dto;

import Groupstudy.example.Groupstudy.Enum.UserCategory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String emailId;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private UserCategory userCategoryName;
}


