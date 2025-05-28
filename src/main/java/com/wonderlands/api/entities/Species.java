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
    @Column(name = "description", length = 10000)
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
    
    
    
 // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public Map<String, String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Map<String, String> descriptions) {
        this.descriptions = descriptions;
    }

    public SpeciesType getType() {
        return type;
    }

    public void setType(SpeciesType type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ConservationStatus getConservationStatus() {
        return conservationStatus;
    }

    public void setConservationStatus(ConservationStatus conservationStatus) {
        this.conservationStatus = conservationStatus;
    }

    public Set<Habitat> getHabitats() {
        return habitats;
    }

    public void setHabitats(Set<Habitat> habitats) {
        this.habitats = habitats;
    }

    public Set<Region> getRegions() {
        return regions;
    }

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }

    public Set<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Set<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    // Métodos útiles para manejar relaciones
    public void addHabitat(Habitat habitat) {
        if (habitats == null) {
            habitats = new HashSet<>();
        }
        habitats.add(habitat);
    }

    public void removeHabitat(Habitat habitat) {
        if (habitats != null) {
            habitats.remove(habitat);
        }
    }

    public void addRegion(Region region) {
        if (regions == null) {
            regions = new HashSet<>();
        }
        regions.add(region);
    }

    public void removeRegion(Region region) {
        if (regions != null) {
            regions.remove(region);
        }
    }

    public void addMultimedia(Multimedia media) {
        if (multimedia == null) {
            multimedia = new HashSet<>();
        }
        media.setSpecies(this);
        multimedia.add(media);
    }

    public void removeMultimedia(Multimedia media) {
        if (multimedia != null) {
            multimedia.remove(media);
            media.setSpecies(null);
        }
    }
    
    
}