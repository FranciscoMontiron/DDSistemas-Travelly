
package com.travellyprueba.travellyprueba.Controller;

import com.travellyprueba.travellyprueba.Entity.Usuario;
import com.travellyprueba.travellyprueba.Repository.UsuarioRepository;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("/api/usuario")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
        
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Integer id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        
        if(!usuarioOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        
        return ResponseEntity.ok(usuarioOptional.get());
        
    }
    
    @GetMapping("/listar")
    public ResponseEntity <List<Usuario>> listarUsuarios(){
        List<Usuario> list = usuarioRepository.findAll();
        return new ResponseEntity(list,HttpStatus.OK);
    }


    @PostMapping("/crear")
    public ResponseEntity<Usuario> guardarUsuario(@Valid @RequestBody Usuario usuario){
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(usuarioGuardado.getId()).toUri();
        
        return ResponseEntity.created(ubicacion).body(usuarioGuardado);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable Integer id, @Valid @RequestBody Usuario usuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        
        if(!usuarioOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        
        usuario.setId(usuarioOptional.get().getId());
        usuarioRepository.save(usuario);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Integer id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        
        if(!usuarioOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        
        usuarioRepository.delete(usuarioOptional.get());
        return ResponseEntity.noContent().build();
    }
}
