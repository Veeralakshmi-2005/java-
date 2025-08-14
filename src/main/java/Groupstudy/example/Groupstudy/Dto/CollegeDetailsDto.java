package Groupstudy.example.Groupstudy.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CollegeDetailsDto {

    private String collegeId;

    @NotBlank(message = "User ID is required")
    private String userId;

    // Common fields
    @NotBlank(message = "College name is required")
    private String collegeName;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "University is required")
    private String university;

    // Conditional fields
    private String teacherIdentificationNumber; // for PROFESSOR
    private String collegeRegistrationNo;       // for INSTITUTION
}

