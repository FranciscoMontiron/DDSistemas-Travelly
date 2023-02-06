
package com.travellyprueba.travellyprueba.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Asiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(columnDefinition="tinyint(1) default 1")
    private Boolean estado;
 
    private Integer numero;
    
    private String clase;
    
    private String asientoColumna;
    
    @OneToOne
    @JoinColumn(name = "pasajero_id")
    private Pasajero pasajero;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "avion_id")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Avion avion;
    
    public Asiento() {
    }

    public Asiento(Boolean estado, Integer numero, String clase, String asientoColumna) {
        this.estado = estado;
        this.numero = numero;
        this.clase = clase;
        this.asientoColumna = asientoColumna;
    }
}
