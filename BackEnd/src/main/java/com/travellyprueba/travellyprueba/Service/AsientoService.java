
package com.travellyprueba.travellyprueba.Service;

import com.travellyprueba.travellyprueba.Entity.Asiento;
import com.travellyprueba.travellyprueba.Repository.AsientoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AsientoService {
    @Autowired AsientoRepository asientoRepository;
    
    public List<Asiento> list(){
        return asientoRepository.findAll();
    }
    
    public Optional<Asiento>getOne(int id){
        return asientoRepository.findById(id);
    }
    public Optional<Asiento>getById(Integer id){
        return asientoRepository.findById(id);
    }
    public void save(Asiento asiento){
        asientoRepository.save(asiento);
    }
    public void delete(int id){
        asientoRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return asientoRepository.existsById(id);
    }
    
    public boolean existById(Integer id){
        return asientoRepository.existsById(id);
    }
}
