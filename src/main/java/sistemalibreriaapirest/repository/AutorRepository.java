package sistemalibreriaapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistemalibreriaapirest.domain.Autor;


@Repository
public interface AutorRepository extends  JpaRepository<Autor, String>{
    
}
