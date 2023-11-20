package com.modis.acciaback.service;

import com.modis.acciaback.security.data.CustomUserDetails;
import com.modis.acciaback.security.data.JwtResponse;
import com.modis.acciaback.payloads.LoginRequest;
import com.modis.acciaback.repository.UserRepository;
import com.modis.acciaback.security.JwtUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	Logger log = LogManager.getLogger(AuthService.class);
	
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtUtils jwtUtils;

    @Autowired
    public AuthService(UserRepository userRepository, AuthenticationManager authManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authManager;
        this.jwtUtils = jwtUtils;
    }

    public JwtResponse login(LoginRequest request) {
    	log.info("-------------------- Je suis login Service -------");
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtUtils.generateJwtToken(auth);

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        return new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getRole()
        );
    }
}
