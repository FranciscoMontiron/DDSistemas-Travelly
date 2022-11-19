
package com.travellyprueba.travellyprueba.Entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;


@Entity
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    private Integer cantidadAsientos;
    
    @NotNull
    private String matricula;
    
    @NotNull
    private String marca;
    
    @OneToMany(mappedBy = "avion", cascade = CascadeType.ALL)
    private Set<Asiento> asientos = new HashSet<>();
  

    public Avion() {
    }

    public Avion(Integer cantidadAsientos, String matricula, String marca) {
        this.cantidadAsientos = cantidadAsientos;
        this.matricula = matricula;
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCantidadAsientos() {
        return cantidadAsientos;
    }

    public void setCantidadAsientos(Integer cantidadAsientos) {
        this.cantidadAsientos = cantidadAsientos;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Set<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(Set<Asiento> asientos) {
        this.asientos = asientos;
        for(Asiento asiento : asientos){
            asiento.setAvion(this);
        }
    }

    
    
    


      
}
