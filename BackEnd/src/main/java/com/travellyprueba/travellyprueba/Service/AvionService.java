
package com.travellyprueba.travellyprueba.Service;

import com.travellyprueba.travellyprueba.Entity.Avion;
import com.travellyprueba.travellyprueba.Repository.AvionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AvionService {
    @Autowired AvionRepository avionRepository;
    
    public List<Avion> list(){
        return avionRepository.findAll();
    }
    
    public Optional<Avion>getOne(int id){
        return avionRepository.findById(id);
    }
    public Optional<Avion>getById(Integer id){
        return avionRepository.findById(id);
    }
    public void save(Avion avion){
        avionRepository.save(avion);
    }
    public void delete(int id){
        avionRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return avionRepository.existsById(id);
    }
    
    public boolean existById(Integer id){
        return avionRepository.existsById(id);
    }
}
