package Groupstudy.example.Groupstudy.Dto;

import Groupstudy.example.Groupstudy.Entity.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDto {
    private UUID userId;
    private String fullName;
    private String emailId;
    private String mobileNumber;
    private boolean isVerified;
    private Role role;
}

