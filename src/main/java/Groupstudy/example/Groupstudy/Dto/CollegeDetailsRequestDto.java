package Groupstudy.example.Groupstudy.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CollegeDetailsRequestDto {
    @NotBlank(message = "College name is required")
    private String collegeName;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "University is required")
    private String university;

    @NotBlank(message = "Teacher Identification Number is required")
    private String teacherIdentificationNumber;

    @NotBlank(message = "College registration number is required")
    private String collegeRegistrationNo;
}
