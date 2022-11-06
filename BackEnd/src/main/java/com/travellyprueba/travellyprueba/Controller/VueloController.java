
package com.travellyprueba.travellyprueba.Controller;

import com.travellyprueba.travellyprueba.Dto.Mensaje;
import com.travellyprueba.travellyprueba.Dto.VueloDto;
import com.travellyprueba.travellyprueba.Entity.Vuelo;
import com.travellyprueba.travellyprueba.Service.VueloService;
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

@RequestMapping("/vuelos")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VueloController {
    
    @Autowired 
    VueloService vueloService;
    
    @GetMapping("/list")
    public ResponseEntity<List<Vuelo>> list(){
        List<Vuelo>list=vueloService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Vuelo> getById(@PathVariable("id") int id){
        if(!vueloService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Vuelo vuelo = vueloService.getOne(id).get();
        return new ResponseEntity(vuelo, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody VueloDto dtoVuelo){
        Vuelo vuelo=new Vuelo(dtoVuelo.getFechaYHoraArribo(),dtoVuelo.getFechaYHoraPartida(),dtoVuelo.getPrecio());
        vueloService.save(vuelo);
        return new ResponseEntity(new Mensaje("Vuelo agregado"),HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?>update(@PathVariable("id")int id,@RequestBody VueloDto dtoVuelo){
        // Validamos si existe el ID
        if(!vueloService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        Vuelo vuelo=vueloService.getOne(id).get();
        vuelo.setFechaYHoraArribo(dtoVuelo.getFechaYHoraArribo());
        vuelo.setFechaYHoraPartida(dtoVuelo.getFechaYHoraPartida());
        vuelo.setPrecio(dtoVuelo.getPrecio());
        vueloService.save(vuelo);
        return new ResponseEntity(new Mensaje("Vuelo Actualizado"),HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id")int id){
    // Validamos si existe el ID
        if(!vueloService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        vueloService.delete(id);
        return new ResponseEntity(new Mensaje("Vuelo eliminado"),HttpStatus.OK);
    }
    
    
}
