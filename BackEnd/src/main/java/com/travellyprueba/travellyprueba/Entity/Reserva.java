
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
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String estado;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaYHora;

    
    
    public Reserva() {
    }

    public Reserva(String estado, Calendar fechaYHora) {
        this.estado = estado;
        this.fechaYHora = fechaYHora;
    }
    
    
}
