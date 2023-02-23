
package com.travellyprueba.travellyprueba.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    
    
    @OneToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    public Pago() {
    }

    public Pago(Calendar fechaYHora, Double monto, Integer cantidadPasajes) {
        this.fechaYHora = fechaYHora;
        this.monto = monto;
    }
    
    
    
}
