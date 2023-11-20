package com.modis.acciaback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.modis.acciaback.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
    
    User findByEmail(String email);

    /*
    @Query("SELECT u FROM User u WHERE u.role = com.modis.acciaback.model.UserRole.ADMIN " +
            "AND NOT EXISTS (SELECT t FROM Ticket t WHERE t.assignedUser.id = u.id AND t.endDate IS NOT NULL)")
            */
    @Query(nativeQuery = true, value = "SELECT u.* FROM users u " +
            "WHERE u.role = 1 AND u.is_status_available is true AND u.id NOT IN (" +
            "SELECT t.assigned_user_id FROM tickets t WHERE t.end_date IS NULL\n" +
            ")")
    List<User> findAvailableAdmins();
}
