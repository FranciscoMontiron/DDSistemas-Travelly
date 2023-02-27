
package com.travellyprueba.travellyprueba.Repository;

import com.travellyprueba.travellyprueba.Entity.Pago;
import com.travellyprueba.travellyprueba.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;


    @Repository
    public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
        /*
        @Query("SELECT p FROM Pago p INNER JOIN Reserva r ON p.reserva = r.id WHERE p.reserva = :reservaId")
        Pago buscarPagoPorReservaId(@PathVariable Integer reservaId);*/
    }
