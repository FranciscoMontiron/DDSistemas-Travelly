
package com.travellyprueba.travellyprueba.Dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AvionDto {
    
    @NotBlank
    private Integer cantidadAsientos;
    @NotBlank
    private String matricula;
    @NotBlank
    private String marca;

    public AvionDto() {
    }

    public AvionDto(Integer cantidadAsientos, String matricula, String marca) {
        this.cantidadAsientos = cantidadAsientos;
        this.matricula = matricula;
        this.marca = marca;
    }
    
    
    
}
