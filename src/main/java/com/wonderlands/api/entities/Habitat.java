package com.wonderlands.api.entities;

import jakarta.persistence.*;

@Entity
public class Habitat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}