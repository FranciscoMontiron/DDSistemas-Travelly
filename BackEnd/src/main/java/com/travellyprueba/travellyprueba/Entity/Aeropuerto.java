
package com.travellyprueba.travellyprueba.Entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Aeropuerto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String codigo;
    
    private String latitud;
    
    private String longitud;
    
    private String nombre;
    
    private String region;
    
    @ManyToOne
    private Pais pais;
    

    public Aeropuerto() {
    }

    public Aeropuerto(String codigo, String latitud, String longitud, String nombre, String region) {
        this.codigo = codigo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.region = region;
    }
    
    
}
