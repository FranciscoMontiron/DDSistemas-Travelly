
package com.travellyprueba.travellyprueba.Controller;

import com.travellyprueba.travellyprueba.Entity.Reserva;
import com.travellyprueba.travellyprueba.Entity.Usuario;
import com.travellyprueba.travellyprueba.Repository.ReservaRepository;
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

@RequestMapping("/api/reservas")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReservaController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ReservaRepository reservaRepository;
    
    @PostMapping("/crear")
    public ResponseEntity<Reserva> guardarReserva(@Valid @RequestBody Reserva reserva){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(reserva.getUsuario().getId());

        if(!usuarioOptional.isPresent()){
                return ResponseEntity.unprocessableEntity().build();
        }

        reserva.setUsuario(usuarioOptional.get());
        Reserva reservaGuardado = reservaRepository.save(reserva);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(reservaGuardado.getId()).toUri();

        return ResponseEntity.created(ubicacion).body(reservaGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> editarReserva(@Valid @RequestBody Reserva reserva, @PathVariable Integer id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(reserva.getUsuario().getId());

        if(!usuarioOptional.isPresent()){
                return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Reserva> reservaOptional = reservaRepository.findById(id);
        if(!reservaOptional.isPresent()){
                return ResponseEntity.unprocessableEntity().build();
        }


        reserva.setUsuario(usuarioOptional.get());
        reserva.setId(reservaOptional.get().getId());
        reservaRepository.save(reserva);

        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Reserva> eliminarReserva(@PathVariable Integer id){
        
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);
        
        reservaRepository.delete(reservaOptional.get());
        
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerReservaPorId(@PathVariable Integer id){
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);
        
        if(!reservaOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        
        return ResponseEntity.ok(reservaOptional.get());
        
    }
    
    @GetMapping("/listar")
    public ResponseEntity <List<Reserva>> listarReservas(){
        List<Reserva> list = reservaRepository.findAll();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
}
