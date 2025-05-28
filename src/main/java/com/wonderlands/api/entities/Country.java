package com.wonderlands.api.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos los argumentos
public class Country {
    @Id
    private String code; // CR, US, etc.
    private String name;
    @OneToMany(mappedBy = "country")
    @JsonIgnore  // ← Esto evita la serialización de regions
    private Set<Region> regions;
    
    
    // Getters y Setters
    	    public String getCode() {
    	        return code;
    	    }

    	    public void setCode(String code) {
    	        this.code = code;
    	    }

    	    public String getName() {
    	        return name;
    	    }

    	    public void setName(String name) {
    	        this.name = name;
    	    }

    	    public Set<Region> getRegions() {
    	        return regions;
    	    }

    	    public void setRegions(Set<Region> regions) {
    	        this.regions = regions;
    	    }
    
    
}
