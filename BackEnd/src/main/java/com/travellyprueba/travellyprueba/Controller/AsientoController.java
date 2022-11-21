
package com.travellyprueba.travellyprueba.Controller;


import com.travellyprueba.travellyprueba.Entity.Asiento;
import com.travellyprueba.travellyprueba.Entity.Avion;
import com.travellyprueba.travellyprueba.Repository.AsientoRepository;
import com.travellyprueba.travellyprueba.Repository.AvionRepository;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("/api/asientos")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AsientoController {
    
    @Autowired
    private AvionRepository avionRepository;
    
    @Autowired
    private AsientoRepository asientoRepository;
    
    @PostMapping("/crear")
    public ResponseEntity<Asiento> guardarAsiento(@Valid @RequestBody Asiento asiento){
		Optional<Avion> avionOptional = avionRepository.findById(asiento.getAvion().getId());
		
		if(!avionOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		asiento.setAvion(avionOptional.get());
		Asiento asientoGuardado = asientoRepository.save(asiento);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(asientoGuardado.getId()).toUri();
		
		return ResponseEntity.created(ubicacion).body(asientoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asiento> guardarAsiento(@Valid @RequestBody Asiento asiento, @PathVariable Integer id){
		Optional<Avion> avionOptional = avionRepository.findById(asiento.getAvion().getId());
		
		if(!avionOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
                
                Optional<Asiento> asientoOptional = asientoRepository.findById(id);
                if(!asientoOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
                
		
		asiento.setAvion(avionOptional.get());
                asiento.setId(asientoOptional.get().getId());
                asientoRepository.save(asiento);
		
		return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Asiento> eliminarAsiento(@PathVariable Integer id){
        
        Optional<Asiento> asientoOptional = asientoRepository.findById(id);
        
        asientoRepository.delete(asientoOptional.get());
        
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Asiento> obtenerAsientoPorId(@PathVariable Integer id){
        Optional<Asiento> asientoOptional = asientoRepository.findById(id);
        
        if(!asientoOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        
        return ResponseEntity.ok(asientoOptional.get());
        
    }
    
    @GetMapping("/listar")
    public ResponseEntity <List<Asiento>> listarAsientos(){
        List<Asiento> list = asientoRepository.findAll();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
}
