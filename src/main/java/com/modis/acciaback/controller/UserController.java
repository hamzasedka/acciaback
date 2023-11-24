package com.modis.acciaback.controller;

import com.modis.acciaback.model.User;
import com.modis.acciaback.repository.UserRepository;
import com.modis.acciaback.security.data.CustomUserDetails;
import com.modis.acciaback.service.AuthService;
import com.modis.acciaback.service.ResetPasswordTokenService;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController

@CrossOrigin(origins = "https://main--cosmic-moonbeam-82881c.netlify.app")
@RequestMapping("users")
public class UserController {
    Logger log = LogManager.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/status")
    @PreAuthorize("isAuthenticated()")
    public boolean getStatus() {
        log.info(" ------- je suis getStatus controller : -------");
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User u = userDetails.getUser();
        return u.isStatusAvailable();
    }

    @PostMapping("/toggleStatus")
    @PreAuthorize("isAuthenticated()")
    public boolean toggleStatus() {
        log.info(" ------- je suis toggleStatus controller : -------");
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User u = userDetails.getUser();
        u.setStatusAvailable(!u.isStatusAvailable());
        userRepository.save(u);
        return u.isStatusAvailable();
    }

    @GetMapping("/allUser")
    public List<User> getAllEmployees() {
        return userRepository.findAll();
    }

    @GetMapping("/{username}") // GET HTTP method
    public User getUser(@PathVariable String username) {
        return userRepository.findUserByUsername(username);
    }

    @Autowired
    private ResetPasswordTokenService userService;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/updatePassword")
    public User processPasswordUpdate(@RequestBody User user, @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword) {
        log.info("********Update password start*********************");

        userService.updatePassword(user, oldPassword, newPassword);
        log.info("********Update password final*********************");
        return user;

        // Effectuer d'autres actions après la mise à jour du mot de passe
    }

}
