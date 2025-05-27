package com.wonderlands.api.entities;

import java.util.*;
import jakarta.persistence.*;

@Entity
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commonName;
    private String scientificName;
    
    @ElementCollection
    @MapKeyColumn(name = "language")
    @Column(name = "description")
    @CollectionTable(name = "species_descriptions", joinColumns = @JoinColumn(name = "species_id"))
    private Map<String, String> descriptions = new HashMap<>();
    
    @Enumerated(EnumType.STRING)
    private SpeciesType type;
    
    @ManyToOne
    private Category category;
    
    @ManyToOne
    private ConservationStatus conservationStatus;
    
    @ManyToMany
    private Set<Habitat> habitats;
    
    @ManyToMany
    private Set<Region> regions;
    
    @OneToMany(mappedBy = "species", cascade = CascadeType.ALL)
    private Set<Multimedia> multimedia;
}