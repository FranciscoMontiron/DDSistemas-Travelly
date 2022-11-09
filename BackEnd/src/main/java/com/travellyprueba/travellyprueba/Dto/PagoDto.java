
package com.travellyprueba.travellyprueba.Dto;

import java.util.Calendar;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter       
public class PagoDto {
    
    @NotBlank
    private Calendar fechaYHora;
    @NotBlank
    private Double monto;
    @NotBlank
    private Integer cantidadPasajes;

    public PagoDto() {
    }

    public PagoDto(Calendar fechaYHora, Double monto, Integer cantidadPasajes) {
        this.fechaYHora = fechaYHora;
        this.monto = monto;
        this.cantidadPasajes = cantidadPasajes;
    }
    
}
