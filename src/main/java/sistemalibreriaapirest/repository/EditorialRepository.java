package sistemalibreriaapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistemalibreriaapirest.domain.Editorial;

@Repository
public interface EditorialRepository extends  JpaRepository<Editorial, String>{
    
    boolean existsByNombre(String nombre);
}
