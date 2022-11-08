
package com.travellyprueba.travellyprueba.Service;

import com.travellyprueba.travellyprueba.Entity.Reserva;
import com.travellyprueba.travellyprueba.Repository.ReservaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReservaService {
    
    @Autowired ReservaRepository reservaRepository;
    
    public List<Reserva> list(){
        return reservaRepository.findAll();
    }
    
    public Optional<Reserva>getOne(int id){
        return reservaRepository.findById(id);
    }
    public Optional<Reserva>getById(Integer id){
        return reservaRepository.findById(id);
    }
    public void save(Reserva reserva){
        reservaRepository.save(reserva);
    }
    public void delete(int id){
        reservaRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return reservaRepository.existsById(id);
    }
    
    public boolean existById(Integer id){
        return reservaRepository.existsById(id);
    }
    
}
