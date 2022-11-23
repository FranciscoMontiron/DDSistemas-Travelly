
package com.travellyprueba.travellyprueba.Security.Service;

import com.travellyprueba.travellyprueba.Security.Entity.Rol;
import com.travellyprueba.travellyprueba.Security.Enums.RolNombre;
import com.travellyprueba.travellyprueba.Security.Repository.iRolRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional //premite presistencia en la bd con trasacciones commit o rollback
public class RolService {
    @Autowired
    iRolRepository irolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return irolRepository.findByRolNombre(rolNombre);
    }
    
    public void save(Rol rol){
        irolRepository.save(rol);
    }
}
