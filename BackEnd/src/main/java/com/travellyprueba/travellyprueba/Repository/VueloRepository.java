
package com.travellyprueba.travellyprueba.Repository;
    
import com.travellyprueba.travellyprueba.Entity.Vuelo;
import java.util.Calendar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Integer>{
    //@Query("SELECT v FROM Vuelo v WHERE fechaYHoraPartida >= :fecha")
    @Query("SELECT v FROM Vuelo v INNER JOIN Aeropuerto ap ON v.aeropuertoPartida = ap.id INNER JOIN Aeropuerto al ON v.aeropuertoLlegada = al.id WHERE ap.region = :origen AND al.region = :destino AND v.fechaYHoraPartida >= :fecha")
    List<Vuelo> findByOriginAndDate(@PathVariable Calendar fecha, @PathVariable String origen, @PathVariable String destino);
}
