
package com.travellyprueba.travellyprueba.Entity;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;

@Entity
@Getter
public class Vuelo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaYHoraArribo;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaYHoraPartida;
    
    private Float precio;
    
    
    @OneToOne
    @JoinColumn(name = "avion_id")
    private Avion avion;
    
    @OneToOne
    @JoinColumn(name = "aeropuero_partida_id")
    private Aeropuerto aeropuertoPartida;
    
    @OneToOne
    @JoinColumn(name = "aeropuero_llegada_id")
    private Aeropuerto aeropuertoLlegada;
    
    
    
    
    

    public Vuelo() {
    }

    public Vuelo(Calendar fechaYHoraArribo, Calendar fechaYHoraPartida, Float precio) {
        this.fechaYHoraArribo = fechaYHoraArribo;
        this.fechaYHoraPartida = fechaYHoraPartida;
        this.precio = precio;
    }    

    public void setId(int id) {
        this.id = id;
    }

    public void setFechaYHoraArribo(Calendar fechaYHoraArribo) {
        this.fechaYHoraArribo = fechaYHoraArribo;
    }

    public void setFechaYHoraPartida(Calendar fechaYHoraPartida) {
        this.fechaYHoraPartida = fechaYHoraPartida;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }
    
}
