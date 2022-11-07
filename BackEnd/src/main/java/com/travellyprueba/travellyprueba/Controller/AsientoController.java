
package com.travellyprueba.travellyprueba.Controller;

import com.travellyprueba.travellyprueba.Dto.AsientoDto;
import com.travellyprueba.travellyprueba.Dto.Mensaje;
import com.travellyprueba.travellyprueba.Entity.Asiento;
import com.travellyprueba.travellyprueba.Service.AsientoService;
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

@RequestMapping("/asientos")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AsientoController {
    
    @Autowired AsientoService asientoService;
    
    @GetMapping("/list")
    public ResponseEntity<List<Asiento>> list(){
        List<Asiento>list=asientoService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Asiento> getById(@PathVariable("id") int id){
        if(!asientoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Asiento asiento = asientoService.getOne(id).get();
        return new ResponseEntity(asiento, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody AsientoDto asientoDto){
        Asiento asiento=new Asiento(asientoDto.getEstado(),asientoDto.getNumero(),asientoDto.getClase(),asientoDto.getAsientoColumna());
        asientoService.save(asiento);
        return new ResponseEntity(new Mensaje("Asiento agregado"),HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?>update(@PathVariable("id")int id,@RequestBody AsientoDto asientoDto){
        // Validamos si existe el ID
        if(!asientoService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        Asiento asiento=asientoService.getOne(id).get();
        asiento.setEstado(asientoDto.getEstado());
        asiento.setNumero(asientoDto.getNumero());
        asiento.setClase(asientoDto.getClase());
        asiento.setAsientoColumna(asientoDto.getAsientoColumna());
        asientoService.save(asiento);
        return new ResponseEntity(new Mensaje("Asiento Actualizado"),HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id")int id){
    // Validamos si existe el ID
        if(!asientoService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        asientoService.delete(id);
        return new ResponseEntity(new Mensaje("Asiento eliminado"),HttpStatus.OK);
    }
}
