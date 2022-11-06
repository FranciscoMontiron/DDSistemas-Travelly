
package com.travellyprueba.travellyprueba.Service;

import com.travellyprueba.travellyprueba.Entity.Vuelo;
import com.travellyprueba.travellyprueba.Repository.VueloRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VueloService {
    
    @Autowired VueloRepository vueloRepository;

    public List<Vuelo> list(){
        return vueloRepository.findAll();
    }
    
    public Optional<Vuelo>getOne(int id){
        return vueloRepository.findById(id);
    }
    public Optional<Vuelo>getById(Integer id){
        return vueloRepository.findById(id);
    }
    public void save(Vuelo vuelo){
        vueloRepository.save(vuelo);
    }
    public void delete(int id){
        vueloRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return vueloRepository.existsById(id);
    }
    
    public boolean existById(Integer id){
        return vueloRepository.existsById(id);
    }
    
}
