package com.wonderlands.api.entities;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;


@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Country country;
}