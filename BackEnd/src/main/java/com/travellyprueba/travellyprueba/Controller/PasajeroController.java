package com.travellyprueba.travellyprueba.Controller;

import com.travellyprueba.travellyprueba.Entity.Pasajero;
import com.travellyprueba.travellyprueba.Repository.PasajeroRepository;
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

@RequestMapping("/api/pasajeros")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PasajeroController {
    
    @Autowired private PasajeroRepository pasajeroRepository;
    
     @GetMapping("/{id}")
    public ResponseEntity<Pasajero> obtenerPasajeroPorId(@PathVariable Integer id){
        Optional<Pasajero> pasajeroOptional = pasajeroRepository.findById(id);
        
        if(!pasajeroOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        
        return ResponseEntity.ok(pasajeroOptional.get());
        
    }
    
    @GetMapping("/listar")
    public ResponseEntity <List<Pasajero>> listarPasajeros(){
        List<Pasajero> list = pasajeroRepository.findAll();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    
    @PostMapping("/crear")
    public ResponseEntity<Pasajero> guardarPasajero(@Valid @RequestBody Pasajero pasajero){
        Pasajero pasajeroGuardado = pasajeroRepository.save(pasajero);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pasajeroGuardado.getId()).toUri();
        
        return ResponseEntity.created(ubicacion).body(pasajeroGuardado);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Pasajero> editarPasajero(@PathVariable Integer id, @Valid @RequestBody Pasajero pasajero){
        Optional<Pasajero> pasajeroOptional = pasajeroRepository.findById(id);
        
        if(!pasajeroOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        
        pasajero.setId(pasajeroOptional.get().getId());
        pasajeroRepository.save(pasajero);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Pasajero> eliminarPasajero(@PathVariable Integer id){
        Optional<Pasajero> pasajeroOptional = pasajeroRepository.findById(id);
        
        if(!pasajeroOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        
        pasajeroRepository.delete(pasajeroOptional.get());
        return ResponseEntity.noContent().build();
    }
    
}
