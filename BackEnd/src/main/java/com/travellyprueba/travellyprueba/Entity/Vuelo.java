
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
public class Vuelo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaYHoraArribo;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaYHoraPartida;
    
    private Float precio;

    public Vuelo() {
    }

    public Vuelo(Calendar fechaYHoraArribo, Calendar fechaYHoraPartida, Float precio) {
        this.fechaYHoraArribo = fechaYHoraArribo;
        this.fechaYHoraPartida = fechaYHoraPartida;
        this.precio = precio;
    }    
     
}
