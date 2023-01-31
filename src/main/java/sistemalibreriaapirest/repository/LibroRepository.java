package sistemalibreriaapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemalibreriaapirest.domain.Libro;

@Repository
public interface LibroRepository extends  JpaRepository<Libro, String>{
    
}
