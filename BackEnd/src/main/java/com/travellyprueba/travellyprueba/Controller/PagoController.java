
package com.travellyprueba.travellyprueba.Controller;

import com.travellyprueba.travellyprueba.Entity.Pago;
import com.travellyprueba.travellyprueba.Entity.Usuario;
import com.travellyprueba.travellyprueba.Repository.PagoRepository;
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

@RequestMapping("/api/pagos")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PagoController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PagoRepository pagoRepository;
    
    @PostMapping("/crear")
    public ResponseEntity<Pago> guardarPago(@Valid @RequestBody Pago pago){
        return new ResponseEntity<>(pagoRepository.save(pago), HttpStatus.CREATED);
        /*
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(pago.getUsuario().getId());

        if(!usuarioOptional.isPresent()){
                return ResponseEntity.unprocessableEntity().build();
        }

        pago.setUsuario(usuarioOptional.get());
        Pago pagoGuardado = pagoRepository.save(pago);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(pagoGuardado.getId()).toUri();

        return ResponseEntity.created(ubicacion).body(pagoGuardado);*/
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> editarReserva(@Valid @RequestBody Pago pago, @PathVariable Integer id){
        
        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        
        if(!pagoOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        
        pago.setId(pagoOptional.get().getId());
        pagoRepository.save(pago);
        return ResponseEntity.noContent().build();
        
        /*
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(pago.getUsuario().getId());

        if(!usuarioOptional.isPresent()){
                return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        if(!pagoOptional.isPresent()){
                return ResponseEntity.unprocessableEntity().build();
        }


        pago.setUsuario(usuarioOptional.get());
        pago.setId(pagoOptional.get().getId());
        pagoRepository.save(pago);

        return ResponseEntity.noContent().build();*/
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Pago> eliminarPago(@PathVariable Integer id){
        
        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        
        pagoRepository.delete(pagoOptional.get());
        
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerReservaPorId(@PathVariable Integer id){
        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        
        if(!pagoOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        
        return ResponseEntity.ok(pagoOptional.get());
        
    }
    
    @GetMapping("/listar")
    public ResponseEntity <List<Pago>> listarReservas(){
        List<Pago> list = pagoRepository.findAll();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/traerPagoDeReserva/{id}")
    public ResponseEntity<Pago> obtenerPagoPorReservaID(@PathVariable Integer id){
        Pago pago = pagoRepository.obtenerPagoPorReservaID(id);
        return new ResponseEntity(pago,HttpStatus.OK);
    }
    
}
