package smart_workspace.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import smart_workspace.auth.dto.LoginRequest;
import smart_workspace.auth.dto.RegisterRequest;
import smart_workspace.company.entity.Company;
import smart_workspace.company.repository.CompanyRepository;
import smart_workspace.user.entity.User;
import smart_workspace.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private  final CompanyRepository companyRepository;
    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void register(RegisterRequest request){
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        Company company= new Company();

        company.setId(UUID.randomUUID());
        company.setName(request.companyName());
        company.setCreatedAt(LocalDateTime.now());
        companyRepository.save(company);


        User user= new User();
        user.setId(UUID.randomUUID());
        user.setCompany(company);
        user.setEmail(request.email());

        // Temporary
        user.setPasswordHash(request.password());

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());

        user.setRole("ADMIN");

        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);


    }



    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("Email: " + user.getEmail());
        System.out.println("Password Hash: " + user.getPasswordHash());

        boolean matches = passwordEncoder.matches(
                request.password(),
                user.getPasswordHash()
        );

        System.out.println("Matches: " + matches);

        if (!matches) {
            throw new RuntimeException("Invalid Credentials");
        }

        return "Login Successful";
    }
}