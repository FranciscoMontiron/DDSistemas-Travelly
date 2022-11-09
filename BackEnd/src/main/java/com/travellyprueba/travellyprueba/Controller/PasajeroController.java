
package com.travellyprueba.travellyprueba.Controller;

import com.travellyprueba.travellyprueba.Dto.Mensaje;
import com.travellyprueba.travellyprueba.Dto.PasajeroDto;
import com.travellyprueba.travellyprueba.Entity.Pasajero;
import com.travellyprueba.travellyprueba.Service.PasajeroService;
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

@RequestMapping("/pasajeros")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PasajeroController {
    
    @Autowired PasajeroService pasajeroService;
    
    @GetMapping("/list")
    public ResponseEntity<List<Pasajero>> list(){
        List<Pasajero>list=pasajeroService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Pasajero> getById(@PathVariable("id") int id){
        if(!pasajeroService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Pasajero pasajero = pasajeroService.getOne(id).get();
        return new ResponseEntity(pasajero, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody PasajeroDto pasajeroDto){
        Pasajero pasajero=new Pasajero(pasajeroDto.getNombre(),pasajeroDto.getApellido(),pasajeroDto.getDni());
        pasajeroService.save(pasajero);
        return new ResponseEntity(new Mensaje("Pasajero agregado"),HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?>update(@PathVariable("id")int id,@RequestBody PasajeroDto pasajeroDto){
        // Validamos si existe el ID
        if(!pasajeroService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        Pasajero pasajero=pasajeroService.getOne(id).get();
        pasajero.setNombre(pasajeroDto.getNombre());
        pasajero.setApellido(pasajeroDto.getApellido());
        pasajero.setDni(pasajeroDto.getDni());
        pasajeroService.save(pasajero);
        return new ResponseEntity(new Mensaje("Pasajero Actualizado"),HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id")int id){
    // Validamos si existe el ID
        if(!pasajeroService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        pasajeroService.delete(id);
        return new ResponseEntity(new Mensaje("Pasajero eliminado"),HttpStatus.OK);
    }
    
}
