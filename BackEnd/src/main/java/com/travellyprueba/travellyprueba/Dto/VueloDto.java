
package com.travellyprueba.travellyprueba.Dto;

import java.util.Calendar;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VueloDto {
    
    @NotBlank
    private Calendar fechaYHoraArribo;
    @NotBlank
    private Calendar fechaYHoraPartida;
    @NotBlank
    private Float precio;

    public VueloDto() {
    }

    public VueloDto(Calendar fechaYHoraArribo, Calendar fechaYHoraPartida, Float precio) {
        this.fechaYHoraArribo = fechaYHoraArribo;
        this.fechaYHoraPartida = fechaYHoraPartida;
        this.precio = precio;
    }
    
    
    
}
