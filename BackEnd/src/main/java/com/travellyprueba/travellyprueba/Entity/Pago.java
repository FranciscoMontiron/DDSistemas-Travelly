
package com.travellyprueba.travellyprueba.Entity;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaYHora;
    
    private Double monto;
    
    private Integer cantidadPasajes;

    public Pago() {
    }

    public Pago(Calendar fechaYHora, Double monto, Integer cantidadPasajes) {
        this.fechaYHora = fechaYHora;
        this.monto = monto;
        this.cantidadPasajes = cantidadPasajes;
    }
    
    
    
}
