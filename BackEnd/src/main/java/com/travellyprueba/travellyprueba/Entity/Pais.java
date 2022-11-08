
package com.travellyprueba.travellyprueba.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Pais {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private Integer codigoPais;
    
    private String nombre;

    public Pais() {
    }

    public Pais(Integer codigoPais, String nombre) {
        this.codigoPais = codigoPais;
        this.nombre = nombre;
    }
    
    
    
}
