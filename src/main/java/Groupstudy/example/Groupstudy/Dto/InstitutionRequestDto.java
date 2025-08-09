package Groupstudy.example.Groupstudy.Dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class InstitutionRequestDto {
    @NotNull(message = "User ID is required")
    private UUID userId;
    private CollegeDetailsRequestDto collegeDetails;
}

