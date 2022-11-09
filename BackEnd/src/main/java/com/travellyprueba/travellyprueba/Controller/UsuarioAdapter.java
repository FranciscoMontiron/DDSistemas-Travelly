
package com.travellyprueba.travellyprueba.Controller;

import com.travellyprueba.travellyprueba.Dto.Mensaje;
import com.travellyprueba.travellyprueba.Dto.UsuarioDto;
import com.travellyprueba.travellyprueba.Entity.Usuario;
import com.travellyprueba.travellyprueba.Service.UsuarioService;
import java.util.List;
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

@RequestMapping("/usuarios")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioAdapter {
    
    @Autowired UsuarioService usuarioService;
    
    @GetMapping("/list")
    public ResponseEntity<List<Usuario>> list(){
        List<Usuario>list=usuarioService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") int id){
        if(!usuarioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioService.getOne(id).get();
        return new ResponseEntity(usuario, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody UsuarioDto usuarioDto){
        Usuario usuario=new Usuario(usuarioDto.getNombre(),usuarioDto.getApellido(),usuarioDto.getCorreo(),usuarioDto.getDni(),usuarioDto.getDireccion());
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Usuario agregado"),HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?>update(@PathVariable("id")int id,@RequestBody UsuarioDto usuarioDto){
        // Validamos si existe el ID
        if(!usuarioService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        Usuario usuario=usuarioService.getOne(id).get();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setCorreo(usuarioDto.getCorreo());
        usuario.setDni(usuarioDto.getDni());
        usuario.setDireccion(usuarioDto.getDireccion());
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Usuario Actualizado"),HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id")int id){
    // Validamos si existe el ID
        if(!usuarioService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        usuarioService.delete(id);
        return new ResponseEntity(new Mensaje("Usuario eliminado"),HttpStatus.OK);
    }
}
