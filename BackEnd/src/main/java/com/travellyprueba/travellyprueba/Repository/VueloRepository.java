
package com.travellyprueba.travellyprueba.Repository;

import com.travellyprueba.travellyprueba.Entity.Vuelo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Integer>{
    @Override
    public Optional<Vuelo> findById(Integer id);
    @Override
    public boolean existsById(Integer id);
}
