
package com.travellyprueba.travellyprueba.Repository;

import com.travellyprueba.travellyprueba.Entity.Pais;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer>{
    @Override
    public Optional<Pais> findById(Integer id);
    @Override
    public boolean existsById(Integer id);
}
