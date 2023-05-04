package sistemalibreriaapirest.service;

import java.util.List;

import sistemalibreriaapirest.domain.Autor;
import sistemalibreriaapirest.dto.AutorDto;

public interface AutorServicio {

    public AutorDto crearAutor(AutorDto libroDto);

    public List<AutorDto> obtenerTodosLosAutores();

    public AutorDto obtenerAutorPorId(String id);

    public AutorDto actualizarAutor(AutorDto libro, String id);

    public void eliminarAutor(String id);
    
    public AutorDto mapearADto(Autor libro);
    
    public Autor mapearAEntidad(AutorDto libroDto);
}
