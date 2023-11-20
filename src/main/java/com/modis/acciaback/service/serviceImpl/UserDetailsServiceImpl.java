package com.modis.acciaback.service.serviceImpl;

import com.modis.acciaback.security.data.CustomUserDetails;
import com.modis.acciaback.model.User;
import com.modis.acciaback.repository.UserRepository;

import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	Logger log = LogManager.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private UserRepository userRepo;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	log.info("-------------------- Je suis loadUserByUsername Service impl -------");
        User user = userRepo.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
    
    public User findAvailableAdmin() {
    	log.info("-------------------- Je suis findAvailableAdmin Service -------");
    	User admin=null;
    
    	List<User> availableAdmins = userRepo.findAvailableAdmins();
		if (availableAdmins.size() > 0) {			
			availableAdmins
					.sort(Comparator.comparingInt(user -> ((User) user).getExperience().ordinal()).reversed());
		
			 admin = availableAdmins.get(0);
    }
		else {
			log.info("-------------------- No available admin -------");
		}
		return admin;
    }
}
