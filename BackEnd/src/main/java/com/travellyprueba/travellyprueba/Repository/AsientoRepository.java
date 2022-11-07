
package com.travellyprueba.travellyprueba.Repository;

import com.travellyprueba.travellyprueba.Entity.Asiento;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsientoRepository extends JpaRepository<Asiento, Integer> {
    @Override
    public Optional<Asiento> findById(Integer id);
    @Override
    public boolean existsById(Integer id);
}
