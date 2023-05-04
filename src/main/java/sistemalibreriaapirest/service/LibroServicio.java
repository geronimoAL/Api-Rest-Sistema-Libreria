package sistemalibreriaapirest.service;

import java.util.List;

import sistemalibreriaapirest.domain.Libro;
import sistemalibreriaapirest.dto.LibroDto;

public interface LibroServicio {

    public LibroDto crearLibro(LibroDto libroDto);

    public List<LibroDto> obtenerTodosLosLibros();

    public List<LibroDto> obtenerLibrosPorAutorYEditorial(String autor,String editorial);

    public LibroDto obtenerLibroPorId(String id);

    public LibroDto actualizarLibro(LibroDto libro, String id);

    public void eliminarLibro(String id);
    
    public LibroDto mapearADto(Libro libro);
    
    public Libro mapearAEntidad(LibroDto libroDto);
}
