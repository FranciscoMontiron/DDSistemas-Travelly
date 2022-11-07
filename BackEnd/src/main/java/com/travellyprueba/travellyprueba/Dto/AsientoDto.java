
package com.travellyprueba.travellyprueba.Dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AsientoDto {
    
    @NotBlank
    private String estado;
    @NotBlank
    private Integer numero;
    @NotBlank
    private String clase;
    @NotBlank
    private String asientoColumna;

    public AsientoDto() {
    }

    public AsientoDto(String estado, Integer numero, String clase, String asientoColumna) {
        this.estado = estado;
        this.numero = numero;
        this.clase = clase;
        this.asientoColumna = asientoColumna;
    }
    
    
    
}
