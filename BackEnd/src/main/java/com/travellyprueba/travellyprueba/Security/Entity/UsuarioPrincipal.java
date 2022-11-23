
package com.travellyprueba.travellyprueba.Security.Entity;

import com.travellyprueba.travellyprueba.Entity.Usuario;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioPrincipal implements UserDetails {
    
    private String nombre;
    private String apellido;
    private String correo;
    private Integer dni;
    private String direccion;
    private String password;
    private String nombreUsuario;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(String nombre, String apellido, String correo, Integer dni, String direccion, String password,String nombreUsuario ,Collection<? extends GrantedAuthority> authorities) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.dni = dni;
        this.direccion = direccion;
        this.password = password;
        this.nombreUsuario = nombreUsuario;
        this.authorities = authorities;
        
    }
    
    public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority>authorities=usuario.getRoles().stream()
            .map(rol->new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors
                    .toList());
        return new UsuarioPrincipal(usuario.getNombre(),usuario.getApellido(),usuario.getCorreo(),usuario.getDni(),usuario.getDireccion(),usuario.getPassword(),usuario.getNombreUsuario(),authorities);
   }
    
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public Integer getDni() {
        return dni;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
