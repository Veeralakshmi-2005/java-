package Groupstudy.example.Groupstudy.Service;

import Groupstudy.example.Groupstudy.Dto.CollegeDetailsRequestDto;
import Groupstudy.example.Groupstudy.Dto.UserRequestDto;
import Groupstudy.example.Groupstudy.Entity.*;
import Groupstudy.example.Groupstudy.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CollegeDetailsRepository collegeDetailsRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final InstitutionRepository institutionRepository;

    public UserService(
            UserRepository userRepository,
            CollegeDetailsRepository collegeDetailsRepository,
            StudentRepository studentRepository,
            ProfessorRepository professorRepository,
            InstitutionRepository institutionRepository
    ) {
        this.userRepository = userRepository;
        this.collegeDetailsRepository = collegeDetailsRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.institutionRepository = institutionRepository;
    }

    @Transactional
    public UUID registerUserWithRole(UserRequestDto userDto, CollegeDetailsRequestDto collegeDto) {
        // 1. Save User
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setEmailId(userDto.getEmailId());
        user.setMobileNumber(userDto.getMobileNumber());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole()); // String now, no .name()
        user.setVerified(false);
        user = userRepository.save(user);

        // 2. Save College Details
        CollegeDetails collegeDetails = new CollegeDetails();
        collegeDetails.setCollegeName(collegeDto.getCollegeName());
        collegeDetails.setLocation(collegeDto.getLocation());
        collegeDetails.setUniversity(collegeDto.getUniversity());

        // Role-specific fields
        switch (userDto.getRole().toLowerCase()) {
            case "professor":
                collegeDetails.setTeacherIdentificationNumber(collegeDto.getTeacherIdentificationNumber());
                break;
            case "institution":
                collegeDetails.setCollegeRegistrationNo(collegeDto.getCollegeRegistrationNo());
                break;
            default:
                // student -> no extra fields
                break;
        }

        collegeDetails = collegeDetailsRepository.save(collegeDetails);

        // 3. Link entities based on role
        switch (userDto.getRole().toLowerCase()) {
            case "student":
                Student student = new Student();
                student.setUser(user);
                student.setCollegeDetails(collegeDetails);
                studentRepository.save(student);
                break;
            case "professor":
                Professor professor = new Professor();
                professor.setUser(user);
                professor.setCollegeDetails(collegeDetails);
                professorRepository.save(professor);
                break;
            case "institution":
                Institution institution = new Institution();
                institution.setUser(user);
                institution.setCollegeDetails(collegeDetails);
                institutionRepository.save(institution);
                break;
        }

        return user.getId();
    }
}






