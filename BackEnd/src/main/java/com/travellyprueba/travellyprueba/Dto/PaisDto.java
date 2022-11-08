
package com.travellyprueba.travellyprueba.Dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaisDto {
    
    @NotBlank
    private Integer codigoPais;
    
    @NotBlank
    private String nombre;

    public PaisDto() {
    }

    public PaisDto(Integer codigoPais, String nombre) {
        this.codigoPais = codigoPais;
        this.nombre = nombre;
    }
    
    
}
