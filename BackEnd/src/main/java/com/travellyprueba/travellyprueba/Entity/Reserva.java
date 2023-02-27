    package com.travellyprueba.travellyprueba.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String estado;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaYHora;
    
    private Float montoFinal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;

    
    @OneToOne
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;

    
    public Reserva() {
    }

    public Reserva(String estado, Calendar fechaYHora,Float montoFinal) {
        this.estado = estado;
        this.fechaYHora = fechaYHora;
        this.montoFinal = montoFinal;
    }

}
