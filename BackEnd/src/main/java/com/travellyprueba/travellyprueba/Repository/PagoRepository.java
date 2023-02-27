
package com.travellyprueba.travellyprueba.Repository;

import com.travellyprueba.travellyprueba.Entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer>{
    @Query("SELECT p FROM Pago p WHERE p.reserva.id = :id")
    Pago obtenerPagoPorReservaID(@Param("id") Integer id);
}
