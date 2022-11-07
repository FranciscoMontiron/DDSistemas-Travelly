
package com.travellyprueba.travellyprueba.Controller;

import com.travellyprueba.travellyprueba.Dto.AvionDto;
import com.travellyprueba.travellyprueba.Dto.Mensaje;
import com.travellyprueba.travellyprueba.Entity.Avion;
import com.travellyprueba.travellyprueba.Service.AvionService;
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

@RequestMapping("/aviones")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AvionController {
    
    @Autowired AvionService avionService;
    
    @GetMapping("/list")
    public ResponseEntity<List<Avion>> list(){
        List<Avion>list=avionService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Avion> getById(@PathVariable("id") int id){
        if(!avionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Avion avion = avionService.getOne(id).get();
        return new ResponseEntity(avion, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody AvionDto avionDto){
        Avion avion=new Avion(avionDto.getCantidadAsientos(),avionDto.getMatricula(),avionDto.getMarca());
        avionService.save(avion);
        return new ResponseEntity(new Mensaje("Avion agregado"),HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?>update(@PathVariable("id")int id,@RequestBody AvionDto avionDto){
        // Validamos si existe el ID
        if(!avionService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        Avion avion=avionService.getOne(id).get();
        avion.setCantidadAsientos(avionDto.getCantidadAsientos());
        avion.setMatricula(avionDto.getMatricula());
        avion.setMarca(avionDto.getMarca());
        avionService.save(avion);
        return new ResponseEntity(new Mensaje("Avion Actualizado"),HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id")int id){
    // Validamos si existe el ID
        if(!avionService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        avionService.delete(id);
        return new ResponseEntity(new Mensaje("Avion eliminado"),HttpStatus.OK);
    }
    
}
