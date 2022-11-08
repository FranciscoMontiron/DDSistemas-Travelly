
package com.travellyprueba.travellyprueba.Controller;

import com.travellyprueba.travellyprueba.Dto.Mensaje;
import com.travellyprueba.travellyprueba.Dto.PaisDto;
import com.travellyprueba.travellyprueba.Entity.Pais;
import com.travellyprueba.travellyprueba.Service.PaisService;
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

@RequestMapping("/paises")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PaisController {
    
    @Autowired PaisService paisService;
    
    @GetMapping("/list")
    public ResponseEntity<List<Pais>> list(){
        List<Pais>list=paisService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Pais> getById(@PathVariable("id") int id){
        if(!paisService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Pais pais = paisService.getOne(id).get();
        return new ResponseEntity(pais, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody PaisDto paisDto){
        Pais pais=new Pais(paisDto.getCodigoPais(),paisDto.getNombre());
        paisService.save(pais);
        return new ResponseEntity(new Mensaje("Pais agregado"),HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?>update(@PathVariable("id")int id,@RequestBody PaisDto paisDto){
        // Validamos si existe el ID
        if(!paisService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        Pais pais=paisService.getOne(id).get();
        pais.setCodigoPais(paisDto.getCodigoPais());
        pais.setNombre(paisDto.getNombre());
        paisService.save(pais);
        return new ResponseEntity(new Mensaje("Pais Actualizado"),HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id")int id){
    // Validamos si existe el ID
        if(!paisService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        paisService.delete(id);
        return new ResponseEntity(new Mensaje("Pais eliminado"),HttpStatus.OK);
    }
}
