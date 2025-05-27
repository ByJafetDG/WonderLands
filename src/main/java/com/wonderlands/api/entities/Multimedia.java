package com.wonderlands.api.entities;

import jakarta.persistence.*;

@Entity
public class Multimedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String type; // "image", "video", "audio"
    private String description;
    @ManyToOne
    private Species species;
}