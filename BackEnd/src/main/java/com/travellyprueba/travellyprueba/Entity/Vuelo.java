
package com.travellyprueba.travellyprueba.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

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
    
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "avion_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Avion avion;
    
    
    
    @OneToMany(mappedBy = "vuelo", cascade = CascadeType.ALL)
    private Set<Aeropuerto> aeropuertos = new HashSet<>();
    
    
    

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

    public void setAeropuertos(Set<Aeropuerto> aeropuertos) {
        this.aeropuertos = aeropuertos;
        for(Aeropuerto aeropuerto : aeropuertos){
            aeropuerto.setVuelo(this);
        }
    }
    
    
    
     
}
