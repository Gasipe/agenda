package no.group.estudos.controller;

import lombok.RequiredArgsConstructor;
import no.group.estudos.dto.user.LoginRequest;
import no.group.estudos.dto.user.LoginResponse;
import no.group.estudos.dto.user.RegisterRequest;
import no.group.estudos.entities.User;
import no.group.estudos.repository.UserRepository;
import no.group.estudos.security.authentication.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        if (repository.findByEmail(registerRequest.email()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        User user = new User();
        user.setUsername(registerRequest.username());
        user.setEmail(registerRequest.email());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));

        repository.save(user);
        return ResponseEntity.ok("Usuario cadastrado");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = service.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
