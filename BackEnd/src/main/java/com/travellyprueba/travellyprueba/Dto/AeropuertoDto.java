
package com.travellyprueba.travellyprueba.Dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AeropuertoDto {
    
    @NotBlank
    private String codigo;
    
    @NotBlank
    private String latitud;
    
    @NotBlank
    private String longitud;
    
    @NotBlank
    private String nombre;
    
    @NotBlank
    private String region;
    
    

    public AeropuertoDto() {
    }

    public AeropuertoDto(String codigo, String latitud, String longitud, String nombre, String region) {
        this.codigo = codigo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.region = region;
    }
    
    
    
}
