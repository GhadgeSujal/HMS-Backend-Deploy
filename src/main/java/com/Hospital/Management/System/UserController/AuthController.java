package com.Hospital.Management.System.UserController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hospital.Management.System.UserRepository.UserRepository;
import com.Hospital.Management.System.loginEntity.User;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User existing = userRepository.findByUsernameAndPassword(
                user.getUsername(), user.getPassword());

        if (existing != null) {
            return ResponseEntity.ok(
                new LoginResponse(existing.getUsername(), existing.getRole())
            );
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid username or password");
    }

    static class LoginResponse {
        public String username;
        public String role;

        public LoginResponse(String username, String role) {
            this.username = username;
            this.role = role;
        }
    }
}
