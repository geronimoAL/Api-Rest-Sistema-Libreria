package sistemalibreriaapirest.service;

import java.util.List;

import sistemalibreriaapirest.domain.Autor;
import sistemalibreriaapirest.dto.AutorDto;

public interface AutorServicio {

    public AutorDto crearPublicacion(AutorDto libroDto);

    public List<AutorDto> obtenerTodasLasPublicaciones();

    public AutorDto obtenerPublicacionPorId(String id);

    public AutorDto actualizarPublicacion(AutorDto libro, String id);

    public void eliminarPublicacion(String id);
    
    public AutorDto mapearADto(Autor libro);
    
    public Autor mapearAEntidad(AutorDto libroDto);
}
