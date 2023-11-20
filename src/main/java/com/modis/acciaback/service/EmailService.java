package com.modis.acciaback.service;

import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	Logger log = LogManager.getLogger(EmailService.class);

    @Value("${spring.mail.username}")
    private String from;
    
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendResetPasswordEmail(String userMail, String resetPasswordToken) throws RuntimeException{
    	log.info("-------------------- Je suis sendResetPasswordEmail du EmailService -------");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        try {
			helper.setFrom(from);
	        helper.setTo(userMail);
	        helper.setSubject("Réinitialisation de mot de passe ");
	        String content = "<p>Vous avez demandé la réinitialisation de votre mot de passe.</p>"
	                + "<p>Voici le token nécessaire à la réinitialisation votre mot de passe: "
	                + resetPasswordToken + "</p>"
	                + "<p>Ce token expirera dans 24 heures.</p>";
	        helper.setText(content, true);
	        log.info("-------------------- Contenu du mail " + content + " -------");
	        mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("-------------------- Echec de l'envoi du mail: " + e.toString());
			throw new RuntimeException("Impossible d'envoyer l'e-mail", e);
		}

    }
    
    
    
    
}
