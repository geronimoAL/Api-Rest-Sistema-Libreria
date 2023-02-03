package sistemalibreriaapirest.service;

import java.util.List;

import sistemalibreriaapirest.domain.Libro;
import sistemalibreriaapirest.dto.LibroDto;

public interface LibroServicio {

    public LibroDto crearPublicacion(LibroDto libroDto);

    public List<LibroDto> obtenerTodasLasPublicaciones();

    public List<LibroDto> obtenerPublicacionesPorAutorYEditorial(String autor,String editorial);

    public LibroDto obtenerPublicacionPorId(String id);

    public LibroDto actualizarPublicacion(LibroDto libro, String id);

    public void eliminarPublicacion(String id);
    
    public LibroDto mapearADto(Libro libro);
    
    public Libro mapearAEntidad(LibroDto libroDto);
}
