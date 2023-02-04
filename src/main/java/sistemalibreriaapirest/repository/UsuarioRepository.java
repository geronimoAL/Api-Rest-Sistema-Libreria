package sistemalibreriaapirest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistemalibreriaapirest.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {
    
    public Optional<Usuario> findByEmail(String email);
    public Boolean existsByEmail(String email);
}
