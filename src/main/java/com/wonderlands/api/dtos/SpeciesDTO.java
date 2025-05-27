package com.wonderlands.api.dtos;

import java.util.List;

public class SpeciesDTO {
    private Long id;
    private String commonName;
    private String scientificName;
    private String description; // Según el idioma solicitado
    private String type;
    private String category;
    private String conservationStatus;
    private List<String> habitats;
    private List<String> regions;
    private List<MultimediaDTO> multimedia;
}