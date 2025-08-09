package Groupstudy.example.Groupstudy.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProfessorResponseDto {
    private UUID professorId;
    private UserResponseDto user;
    private CollegeDetailsResponseDto collegeDetails;
}
