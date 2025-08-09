package Groupstudy.example.Groupstudy.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class InstitutionResponseDto {
    private UUID institutionId;
    private UserResponseDto user;
    private CollegeDetailsResponseDto collegeDetails;
}

