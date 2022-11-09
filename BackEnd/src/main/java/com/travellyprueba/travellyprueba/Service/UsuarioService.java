
package com.travellyprueba.travellyprueba.Service;

import com.travellyprueba.travellyprueba.Entity.Usuario;
import com.travellyprueba.travellyprueba.Repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {
    
    @Autowired UsuarioRepository usuarioRepository;
    
    public List<Usuario> list(){
        return usuarioRepository.findAll();
    }
    
    public Optional<Usuario>getOne(int id){
        return usuarioRepository.findById(id);
    }
    public Optional<Usuario>getById(Integer id){
        return usuarioRepository.findById(id);
    }
    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    public void delete(int id){
        usuarioRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return usuarioRepository.existsById(id);
    }
    
    public boolean existById(Integer id){
        return usuarioRepository.existsById(id);
    }
    
}
