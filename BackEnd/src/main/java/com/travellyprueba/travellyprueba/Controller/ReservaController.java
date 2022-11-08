
package com.travellyprueba.travellyprueba.Controller;

import com.travellyprueba.travellyprueba.Dto.Mensaje;
import com.travellyprueba.travellyprueba.Dto.ReservaDto;
import com.travellyprueba.travellyprueba.Entity.Reserva;
import com.travellyprueba.travellyprueba.Service.ReservaService;
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

@RequestMapping("/reservas")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReservaController {
    
    @Autowired ReservaService reservaService;
    
    @GetMapping("/list")
    public ResponseEntity<List<Reserva>> list(){
        List<Reserva>list=reservaService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Reserva> getById(@PathVariable("id") int id){
        if(!reservaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Reserva reserva = reservaService.getOne(id).get();
        return new ResponseEntity(reserva, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody ReservaDto reservaDto){
        Reserva reserva=new Reserva(reservaDto.getEstado(),reservaDto.getFechaYHora());
        reservaService.save(reserva);
        return new ResponseEntity(new Mensaje("Reserva agregada"),HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?>update(@PathVariable("id")int id,@RequestBody ReservaDto reservaDto){
        // Validamos si existe el ID
        if(!reservaService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        Reserva reserva=reservaService.getOne(id).get();
        reserva.setEstado(reservaDto.getEstado());
        reserva.setFechaYHora(reservaDto.getFechaYHora());
        reservaService.save(reserva);
        return new ResponseEntity(new Mensaje("Reserva Actualizada"),HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id")int id){
    // Validamos si existe el ID
        if(!reservaService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        reservaService.delete(id);
        return new ResponseEntity(new Mensaje("Reserva eliminada"),HttpStatus.OK);
    }
    
}
