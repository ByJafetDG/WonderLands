package com.wonderlands.api.entities;

import jakarta.persistence.*;

@Entity
public class ConservationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status; // "En peligro", "Vulnerable", etc.
    private String abbreviation; // "EN", "VU", etc.
}