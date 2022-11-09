
package com.travellyprueba.travellyprueba.Dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioDto {
    
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String correo;
    @NotBlank
    private Integer dni;
    @NotBlank
    private String direccion;

    public UsuarioDto() {
    }

    public UsuarioDto(String nombre, String apellido, String correo, Integer dni, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.dni = dni;
        this.direccion = direccion;
    }
    
    
    
}
