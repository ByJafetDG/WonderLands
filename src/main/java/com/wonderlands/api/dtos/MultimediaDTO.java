package com.wonderlands.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultimediaDTO {
    private Long id;
    private String url;
    private String type; // "image", "video", "audio"
    private String description;
    private String mediaType; // MIME type (opcional, ej. "image/jpeg")
    private Integer width; // Para imágenes/videos (opcional)
    private Integer height; // Para imágenes/videos (opcional)
    private Long fileSize; // En bytes (opcional)
    private String credit; // Atribución/créditos (opcional)
}