
package com.travellyprueba.travellyprueba.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private Integer cantidadAsientos;
    
    private String matricula;
    
    private String marca;

    public Avion() {
    }

    public Avion(Integer cantidadAsientos, String matricula, String marca) {
        this.cantidadAsientos = cantidadAsientos;
        this.matricula = matricula;
        this.marca = marca;
    }
      
}
