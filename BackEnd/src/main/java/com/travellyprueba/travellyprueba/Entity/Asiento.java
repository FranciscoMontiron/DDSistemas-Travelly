
package com.travellyprueba.travellyprueba.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Asiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String estado;
    
    private Integer numero;
    
    private String clase;
    
    private String asientoColumna;

    public Asiento() {
    }

    public Asiento(String estado, Integer numero, String clase, String asientoColumna) {
        this.estado = estado;
        this.numero = numero;
        this.clase = clase;
        this.asientoColumna = asientoColumna;
    }
}
