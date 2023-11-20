package com.modis.acciaback.model;

import com.modis.acciaback.repository.UserRepository;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    private String prenom;

    private String username;

    private String email;

    private String password;

    private UserRole role;

    private UserExperience experience;

    private boolean isStatusAvailable;

    @OneToMany
    private List<Ticket> assignedTickets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isStatusAvailable() {
        return isStatusAvailable;
    }

    public void setStatusAvailable(boolean statusAvailable) {
        isStatusAvailable = statusAvailable;
    }

    public List<Ticket> getAssignedTickets() {
        return assignedTickets;
    }

    public void setAssignedTickets(List<Ticket> assignedTickets) {
        this.assignedTickets = assignedTickets;
    }

    public UserExperience getExperience() {
        return experience;
    }

    public void setExperience(UserExperience experience) {
        this.experience = experience;
    }
    
    
    
    
}
