
package com.travellyprueba.travellyprueba.Service;

import com.travellyprueba.travellyprueba.Entity.Pais;
import com.travellyprueba.travellyprueba.Repository.PaisRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PaisService {
    
    @Autowired PaisRepository paisRepository;
    
    public List<Pais> list(){
        return paisRepository.findAll();
    }
    
    public Optional<Pais>getOne(int id){
        return paisRepository.findById(id);
    }
    public Optional<Pais>getById(Integer id){
        return paisRepository.findById(id);
    }
    public void save(Pais pais){
        paisRepository.save(pais);
    }
    public void delete(int id){
        paisRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return paisRepository.existsById(id);
    }
    
    public boolean existById(Integer id){
        return paisRepository.existsById(id);
    }
}
