

package com.travellyprueba.travellyprueba.Repository;

import com.travellyprueba.travellyprueba.Entity.Pasajero;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasajeroRepository extends JpaRepository<Pasajero, Integer> {
}
