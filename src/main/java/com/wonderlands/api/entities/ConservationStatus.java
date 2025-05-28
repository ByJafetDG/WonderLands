package com.wonderlands.api.entities;

import jakarta.persistence.*;

@Entity
public class ConservationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status; // "En peligro", "Vulnerable", etc.
    private String abbreviation; // "EN", "VU", etc.
    
 // Getters
    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    
}