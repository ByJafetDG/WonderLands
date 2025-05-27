package com.wonderlands.api.entities;

import java.util.Set;

import jakarta.persistence.*;


@Entity
public class Country {
    @Id
    private String code; // CR, US, etc.
    private String name;
    @OneToMany(mappedBy = "country")
    private Set<Region> regions;
}
