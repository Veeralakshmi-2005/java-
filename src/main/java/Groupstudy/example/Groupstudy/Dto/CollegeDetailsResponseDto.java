package Groupstudy.example.Groupstudy.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CollegeDetailsResponseDto {
    private UUID collegeId;
    private String collegeName;
    private String location;
    private String university;
    private String teacherIdentificationNumber;
    private String collegeRegistrationNo;
}

