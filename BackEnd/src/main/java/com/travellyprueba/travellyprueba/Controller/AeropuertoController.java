
package com.travellyprueba.travellyprueba.Controller;

import com.travellyprueba.travellyprueba.Dto.AeropuertoDto;
import com.travellyprueba.travellyprueba.Dto.Mensaje;
import com.travellyprueba.travellyprueba.Entity.Aeropuerto;
import com.travellyprueba.travellyprueba.Service.AeropuertoService;
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

@RequestMapping("/aeropuertos")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AeropuertoController {
    
    @Autowired AeropuertoService aeropuertoService;
    
    @GetMapping("/list")
    public ResponseEntity<List<Aeropuerto>> list(){
        List<Aeropuerto>list=aeropuertoService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Aeropuerto> getById(@PathVariable("id") int id){
        if(!aeropuertoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Aeropuerto aeropuerto = aeropuertoService.getOne(id).get();
        return new ResponseEntity(aeropuerto, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody AeropuertoDto aeropuertoDto){
        Aeropuerto aeropuerto=new Aeropuerto(aeropuertoDto.getCodigo(),aeropuertoDto.getLatitud(),aeropuertoDto.getLongitud(),aeropuertoDto.getNombre(),aeropuertoDto.getRegion());
        aeropuertoService.save(aeropuerto);
        return new ResponseEntity(new Mensaje("Aeropuerto agregado"),HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?>update(@PathVariable("id")int id,@RequestBody AeropuertoDto aeropuertoDto){
        // Validamos si existe el ID
        if(!aeropuertoService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        Aeropuerto aeropuerto=aeropuertoService.getOne(id).get();
        aeropuerto.setCodigo(aeropuertoDto.getCodigo());
        aeropuerto.setLatitud(aeropuertoDto.getLatitud());
        aeropuerto.setLongitud(aeropuertoDto.getLongitud());
        aeropuerto.setNombre(aeropuertoDto.getNombre());
        aeropuerto.setRegion(aeropuertoDto.getRegion());
        aeropuertoService.save(aeropuerto);
        return new ResponseEntity(new Mensaje("Aeropuerto Actualizado"),HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id")int id){
    // Validamos si existe el ID
        if(!aeropuertoService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        aeropuertoService.delete(id);
        return new ResponseEntity(new Mensaje("Aeropuerto eliminado"),HttpStatus.OK);
    }
}
