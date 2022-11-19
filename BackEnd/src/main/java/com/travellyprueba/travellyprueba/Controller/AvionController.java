
package com.travellyprueba.travellyprueba.Controller;

import com.travellyprueba.travellyprueba.Entity.Avion;
import com.travellyprueba.travellyprueba.Repository.AvionRepository;
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

@RestController
@RequestMapping("/api/avion")
@CrossOrigin(origins = "http://localhost:4200")
public class AvionController {
    
    @Autowired
    private AvionRepository avionRepository;
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Avion> obtenerAvionPorId(@PathVariable Integer id){
        Optional<Avion> avionOptional = avionRepository.findById(id);
        
        if(!avionOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        
        return ResponseEntity.ok(avionOptional.get());
        
    }
    
    @GetMapping("/listar")
    public ResponseEntity <List<Avion>> listarAviones(){
        List<Avion> list = avionRepository.findAll();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    
    @PostMapping("/crear")
    public ResponseEntity<Avion> guardarAvion(@Valid @RequestBody Avion avion){
        Avion avionGuardado = avionRepository.save(avion);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(avionGuardado.getId()).toUri();
        
        return ResponseEntity.created(ubicacion).body(avionGuardado);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Avion> editarAvion(@PathVariable Integer id, @Valid @RequestBody Avion avion){
        Optional<Avion> avionOptional = avionRepository.findById(id);
        
        if(!avionOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        
        avion.setId(avionOptional.get().getId());
        avionRepository.save(avion);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Avion> eliminarAvion(@PathVariable Integer id){
        Optional<Avion> avionOptional = avionRepository.findById(id);
        
        if(!avionOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        
        avionRepository.delete(avionOptional.get());
        return ResponseEntity.noContent().build();
    }
    
    
}
