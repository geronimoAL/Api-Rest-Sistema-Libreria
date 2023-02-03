package sistemalibreriaapirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sistemalibreriaapirest.domain.Libro;

@Repository
public interface LibroRepository extends  JpaRepository<Libro, String>{
    
    @Query("SELECT F FROM Libro F WHERE  F.autor.id = :autor and F.editorial.id = :editorial")
    public List<Libro>librosAutorYEditorial(@Param("autor")String autor,@Param("editorial")String editorial);


}
