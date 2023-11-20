package com.modis.acciaback.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.modis.acciaback.model.ResetPasswordToken;
import com.modis.acciaback.model.User;
import com.modis.acciaback.repository.ResetPasswordTokenRepository;
import com.modis.acciaback.repository.UserRepository;

@Service
public class ResetPasswordTokenService {
	Logger log = LogManager.getLogger(ResetPasswordTokenService.class);
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	
	@Autowired
	private ResetPasswordTokenRepository resetPasswordTokenRepository;
	    
	public ResetPasswordToken generateResetPasswordToken(User user) {
		log.info("-------------------- Je suis ResetPasswordToken Service -------");
		
		ResetPasswordToken old_resetPasswordToken = resetPasswordTokenRepository.findByUser(user);
		if (old_resetPasswordToken != null) {
			resetPasswordTokenRepository.delete(old_resetPasswordToken);
		}
		String token = UUID.randomUUID().toString();
	    ResetPasswordToken resetPasswordToken = new ResetPasswordToken(user, token, LocalDateTime.now());
	    resetPasswordToken = resetPasswordTokenRepository.save(resetPasswordToken);
	    return resetPasswordToken;
	}

	@Autowired 
	UserRepository userRepo;
	
	public void updatePassword(User user, String oldPassword, String newPassword) {
		
    	User userRes = userRepo.findByEmail(user.getEmail());

	    // Vérification de l'ancien mot de passe
	    if (passwordEncoder.matches(oldPassword, userRes.getPassword())) {
	        // Encodage du nouveau mot de passe
	    	
	    	
	    	   System.out.println(user.getPassword());
	        log.info("*****************encodedPassword before***************" );
	        
	    	   System.out.println(user.getPassword());


	         String encodedPassword = passwordEncoder.encode(newPassword);
	        log.info("*****************encodedPassword after***************",encodedPassword );
	    
	        userRes.setPassword (encodedPassword);
	        log.info("*****************encodedPassword after***************",encodedPassword );
	    	userRepo.save(userRes);

	        System.out.println(encodedPassword);
	        log.info("///////////////////////////");

	        System.out.println(user.getPassword());

log.info("Le mot de passe a été mis à jour");
	    } else {
	    	log.info("Error mot de passe ancien  ");

	    }
	}
}