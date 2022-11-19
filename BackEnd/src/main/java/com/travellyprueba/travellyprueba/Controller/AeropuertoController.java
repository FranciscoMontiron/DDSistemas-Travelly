
package com.travellyprueba.travellyprueba.Controller;

import com.travellyprueba.travellyprueba.Entity.Aeropuerto;
import com.travellyprueba.travellyprueba.Entity.Pais;
import com.travellyprueba.travellyprueba.Repository.AeropuertoRepository;
import com.travellyprueba.travellyprueba.Repository.PaisRepository;
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

@RequestMapping("/api/aeropuertos")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AeropuertoController {
    
    @Autowired
    private PaisRepository paisRepository;
    
    @Autowired
    private AeropuertoRepository aeropuertoRepository;
    
    @PostMapping("/crear")
    public ResponseEntity<Aeropuerto> guardarAeropuerto(@Valid @RequestBody Aeropuerto aeropuerto){
		Optional<Pais> paisOptional = paisRepository.findById(aeropuerto.getPais().getId());
		
		if(!paisOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		aeropuerto.setPais(paisOptional.get());
		Aeropuerto aeropuertoGuardado = aeropuertoRepository.save(aeropuerto);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(aeropuertoGuardado.getId()).toUri();
		
		return ResponseEntity.created(ubicacion).body(aeropuertoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aeropuerto> guardarAeropuerto(@Valid @RequestBody Aeropuerto aeropuerto, @PathVariable Integer id){
		Optional<Pais> paisOptional = paisRepository.findById(aeropuerto.getPais().getId());
		
		if(!paisOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
                
                Optional<Aeropuerto> aeropuertoOptional = aeropuertoRepository.findById(id);
                if(!aeropuertoOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
                
		
		aeropuerto.setPais(paisOptional.get());
                aeropuerto.setId(aeropuertoOptional.get().getId());
                aeropuertoRepository.save(aeropuerto);
		
		return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Aeropuerto> eliminarAeropuerto(@PathVariable Integer id){
        
        Optional<Aeropuerto> aeropuertoOptional = aeropuertoRepository.findById(id);
        
        aeropuertoRepository.delete(aeropuertoOptional.get());
        
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Aeropuerto> obtenerAeropuertoPorId(@PathVariable Integer id){
        Optional<Aeropuerto> aeropuertoOptional = aeropuertoRepository.findById(id);
        
        if(!aeropuertoOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        
        return ResponseEntity.ok(aeropuertoOptional.get());
        
    }
    
    @GetMapping("/listar")
    public ResponseEntity <List<Aeropuerto>> listarAeropuertos(){
        List<Aeropuerto> list = aeropuertoRepository.findAll();
        return new ResponseEntity(list,HttpStatus.OK);
    }
}
