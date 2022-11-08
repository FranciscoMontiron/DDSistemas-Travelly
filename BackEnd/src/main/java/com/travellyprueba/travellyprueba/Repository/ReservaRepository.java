
package com.travellyprueba.travellyprueba.Repository;

import com.travellyprueba.travellyprueba.Entity.Reserva;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
    @Override
    public Optional<Reserva> findById(Integer id);
    @Override
    public boolean existsById(Integer id);
    
}
