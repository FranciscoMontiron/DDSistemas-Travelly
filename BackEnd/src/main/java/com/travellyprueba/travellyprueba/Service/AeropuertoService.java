
package com.travellyprueba.travellyprueba.Service;

import com.travellyprueba.travellyprueba.Entity.Aeropuerto;
import com.travellyprueba.travellyprueba.Repository.AeropuertoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AeropuertoService {
    
    @Autowired AeropuertoRepository aeropuertoRepository;
    
    public List<Aeropuerto> list(){
        return aeropuertoRepository.findAll();
    }
    
    public Optional<Aeropuerto>getOne(int id){
        return aeropuertoRepository.findById(id);
    }
    public Optional<Aeropuerto>getById(Integer id){
        return aeropuertoRepository.findById(id);
    }
    public void save(Aeropuerto aeropuerto){
        aeropuertoRepository.save(aeropuerto);
    }
    public void delete(int id){
        aeropuertoRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return aeropuertoRepository.existsById(id);
    }
    
    public boolean existById(Integer id){
        return aeropuertoRepository.existsById(id);
    }
    
}
