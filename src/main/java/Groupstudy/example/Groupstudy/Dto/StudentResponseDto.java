package Groupstudy.example.Groupstudy.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class StudentResponseDto {
    private UUID studentId;
    private UserResponseDto user;
    private CollegeDetailsResponseDto collegeDetails;
}

