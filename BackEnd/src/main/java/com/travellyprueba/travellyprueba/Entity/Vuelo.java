
package com.travellyprueba.travellyprueba.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
