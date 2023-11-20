package com.modis.acciaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modis.acciaback.model.ResetPasswordToken;
import com.modis.acciaback.model.User;

@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Integer> {
    ResetPasswordToken findByToken(String token);
    
    ResetPasswordToken findByUser(User user);
}
