
package com.travellyprueba.travellyprueba.Controller;

import com.travellyprueba.travellyprueba.Dto.Mensaje;
import com.travellyprueba.travellyprueba.Dto.PagoDto;
import com.travellyprueba.travellyprueba.Entity.Pago;
import com.travellyprueba.travellyprueba.Service.PagoService;
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

@RequestMapping("/pagos")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PagoController {
    
    @Autowired PagoService pagoService;
    
    @GetMapping("/list")
    public ResponseEntity<List<Pago>> list(){
        List<Pago>list=pagoService.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Pago> getById(@PathVariable("id") int id){
        if(!pagoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Pago pago = pagoService.getOne(id).get();
        return new ResponseEntity(pago, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody PagoDto pagoDto){
        Pago pago=new Pago(pagoDto.getFechaYHora(),pagoDto.getMonto(),pagoDto.getCantidadPasajes());
        pagoService.save(pago);
        return new ResponseEntity(new Mensaje("Pago agregado"),HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?>update(@PathVariable("id")int id,@RequestBody PagoDto pagoDto){
        // Validamos si existe el ID
        if(!pagoService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        Pago pago=pagoService.getOne(id).get();
        pago.setFechaYHora(pagoDto.getFechaYHora());
        pago.setMonto(pagoDto.getMonto());
        pago.setCantidadPasajes(pagoDto.getCantidadPasajes());
        pagoService.save(pago);
        return new ResponseEntity(new Mensaje("Pago Actualizado"),HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id")int id){
    // Validamos si existe el ID
        if(!pagoService.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),HttpStatus.BAD_REQUEST);
        pagoService.delete(id);
        return new ResponseEntity(new Mensaje("Pago eliminado"),HttpStatus.OK);
    }
    
}
