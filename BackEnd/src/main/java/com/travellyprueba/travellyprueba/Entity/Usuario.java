
package com.travellyprueba.travellyprueba.Entity;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    
    private String apellido;
    
    private String correo;
    
    private Integer dni;
    
    private String direccion;
    
    @OneToMany
    @JoinColumn(name = "id_usuario")
    private List<Pago> pagos;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String correo, Integer dni, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.dni = dni;
        this.direccion = direccion;
    }
}
