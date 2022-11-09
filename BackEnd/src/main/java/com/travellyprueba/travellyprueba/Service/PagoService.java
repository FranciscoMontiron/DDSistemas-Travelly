
package com.travellyprueba.travellyprueba.Service;

import com.travellyprueba.travellyprueba.Entity.Pago;
import com.travellyprueba.travellyprueba.Repository.PagoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PagoService {
    
    @Autowired PagoRepository pagoRepository;
    
    public List<Pago> list(){
        return pagoRepository.findAll();
    }
    
    public Optional<Pago>getOne(int id){
        return pagoRepository.findById(id);
    }
    public Optional<Pago>getById(Integer id){
        return pagoRepository.findById(id);
    }
    public void save(Pago pago){
        pagoRepository.save(pago);
    }
    public void delete(int id){
        pagoRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return pagoRepository.existsById(id);
    }
    
    public boolean existById(Integer id){
        return pagoRepository.existsById(id);
    }
    
}
