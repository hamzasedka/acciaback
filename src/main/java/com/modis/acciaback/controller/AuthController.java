package com.modis.acciaback.controller;

import com.modis.acciaback.model.User;
import com.modis.acciaback.payloads.SignupRequest;
import com.modis.acciaback.repository.UserRepository;
import com.modis.acciaback.security.data.JwtResponse;
import com.modis.acciaback.payloads.LoginRequest;
import com.modis.acciaback.service.AuthService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "https://main--cosmic-moonbeam-82881c.netlify.app")
public class AuthController {
    Logger log = LogManager.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginInfos) {
        log.info(" ------- je suis login controller : -------");
        return ResponseEntity.ok(authService.login(loginInfos));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        log.info(" ------- je suis registerUser controller : -------");
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User();

        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setRole(signUpRequest.getRole());
        user.setNom(signUpRequest.getNom());
        user.setPrenom(signUpRequest.getPrenom());
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}
