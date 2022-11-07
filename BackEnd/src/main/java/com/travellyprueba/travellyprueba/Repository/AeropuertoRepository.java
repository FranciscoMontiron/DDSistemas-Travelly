
package com.travellyprueba.travellyprueba.Repository;

import com.travellyprueba.travellyprueba.Entity.Aeropuerto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Integer> {
    @Override
    public Optional<Aeropuerto> findById(Integer id);
    @Override
    public boolean existsById(Integer id);    
}
