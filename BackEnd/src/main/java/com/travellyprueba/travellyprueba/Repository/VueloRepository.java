
package com.travellyprueba.travellyprueba.Repository;
    
import com.travellyprueba.travellyprueba.Entity.Vuelo;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Integer>{}
