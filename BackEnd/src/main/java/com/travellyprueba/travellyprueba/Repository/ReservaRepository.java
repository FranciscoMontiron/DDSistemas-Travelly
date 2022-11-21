
package com.travellyprueba.travellyprueba.Repository;

import com.travellyprueba.travellyprueba.Entity.Reserva;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
}
