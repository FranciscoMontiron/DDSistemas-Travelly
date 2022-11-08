
package com.travellyprueba.travellyprueba.Dto;

import java.util.Calendar;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReservaDto {
    
    @NotBlank
    private String estado;
    
    @NotBlank
    private Calendar fechaYHora;
    
    @NotBlank
    public ReservaDto() {
    }

    public ReservaDto(String estado, Calendar fechaYHora) {
        this.estado = estado;
        this.fechaYHora = fechaYHora;
    }
    
    
    
}
