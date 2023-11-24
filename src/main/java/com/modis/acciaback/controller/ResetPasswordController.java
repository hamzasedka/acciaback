package com.modis.acciaback.controller;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.modis.acciaback.model.ResetPasswordToken;
import com.modis.acciaback.model.User;
import com.modis.acciaback.payloads.ResetPasswordRequest;
import com.modis.acciaback.repository.ResetPasswordTokenRepository;
import com.modis.acciaback.repository.UserRepository;
import com.modis.acciaback.service.EmailService;
import com.modis.acciaback.service.ResetPasswordTokenService;

@RestController
@RequestMapping("password")
@CrossOrigin(origins = "https://main--cosmic-moonbeam-82881c.netlify.app")
public class ResetPasswordController {
    Logger log = LogManager.getLogger(ResetPasswordController.class);

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResetPasswordTokenRepository resetPasswordTokenRepository;

    @Autowired
    private ResetPasswordTokenService resetPasswordTokenService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam("mail") String userMail) {
        log.info("-------------------- Je suis resetPassword de ResetPasswordController -------");
        User user = userRepository.findByEmail(userMail);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        ResetPasswordToken resetPasswordToken = resetPasswordTokenService.generateResetPasswordToken(user);
        if (resetPasswordToken == null) {
            return ResponseEntity.badRequest()
                    .body("Echec de la génération du token de réinitialisation de mot de passe.");
        }

        try {
            emailService.sendResetPasswordEmail(user.getEmail(), resetPasswordToken.getToken());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Echec de l'envoi du mail de réinitialisation de mot de passe.");
        } finally {
            return ResponseEntity.ok("Un mail de réinitialisation de mot de passe a été envoyé.");
        }
    }

    @PostMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        log.info("-------------------- Je suis updatePassword de ResetPasswordController -------");

        ResetPasswordToken token = resetPasswordTokenRepository.findByToken(resetPasswordRequest.getToken());
        if (token == null) {
            return ResponseEntity.badRequest().body("Le jeton de réinitialisation de mot de passe est invalide.");
        }
        LocalDateTime tokenExpirationTime = token.getDateCreated().plusHours(24);
        if (LocalDateTime.now().isAfter(tokenExpirationTime)) {
            resetPasswordTokenRepository.delete(token);
            return ResponseEntity.badRequest().body("Le jeton de réinitialisation de mot de passe a expiré.");
        }
        User user = token.getUser();
        user.setPassword(encoder.encode(resetPasswordRequest.getNewPassword()));
        userRepository.save(user);
        resetPasswordTokenRepository.delete(token);
        return ResponseEntity.ok("Votre mot de passe a été mis à jour avec succès.");
    }

}
