
package com.travellyprueba.travellyprueba.Service;

import com.travellyprueba.travellyprueba.Entity.Pasajero;
import com.travellyprueba.travellyprueba.Repository.PasajeroRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PasajeroService {
    
    @Autowired PasajeroRepository pasajeroRepository;
    
    public List<Pasajero> list(){
        return pasajeroRepository.findAll();
    }
    
    public Optional<Pasajero>getOne(int id){
        return pasajeroRepository.findById(id);
    }
    public Optional<Pasajero>getById(Integer id){
        return pasajeroRepository.findById(id);
    }
    public void save(Pasajero pasajero){
        pasajeroRepository.save(pasajero);
    }
    public void delete(int id){
        pasajeroRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return pasajeroRepository.existsById(id);
    }
    
    public boolean existById(Integer id){
        return pasajeroRepository.existsById(id);
    }
}
