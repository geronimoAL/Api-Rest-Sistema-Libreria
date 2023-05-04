package sistemalibreriaapirest.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistemalibreriaapirest.domain.Autor;
import sistemalibreriaapirest.domain.Editorial;
import sistemalibreriaapirest.domain.Libro;
import sistemalibreriaapirest.dto.LibroDto;
import sistemalibreriaapirest.errors.ResourceBadRequest;
import sistemalibreriaapirest.errors.ResourceNotFoundException;
import sistemalibreriaapirest.repository.AutorRepository;
import sistemalibreriaapirest.repository.EditorialRepository;
import sistemalibreriaapirest.repository.LibroRepository;
import sistemalibreriaapirest.service.LibroServicio;

@Service
public class LibroServicioImpl implements LibroServicio {

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private EditorialRepository editorialRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LibroDto crearLibro(LibroDto libroDto) {
        if(libroRepository.existsByTitulo(libroDto.getTitulo())){
            throw new ResourceBadRequest("El Titulo ingresado ya existe en la base de datos");
        }    
        Libro libro = mapearAEntidad(libroDto);
        Autor autor=buscarAutor(libro.getAutor().getId());
        Editorial editorial=buscarEditorial(libroDto.getEditorial().getId()); 
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        Libro libroGuardado = libroRepository.save(libro);
        LibroDto libroRespuesta = mapearADto(libroGuardado);
        return libroRespuesta;
    }

    @Override
    public List<LibroDto> obtenerTodosLosLibros() {
        List<Libro> librosGuardados = libroRepository.findAll();
        return librosGuardados.stream().map(libro -> mapearADto(libro)).collect(Collectors.toList());
    }


    @Override
    public LibroDto obtenerLibroPorId(String id) {
        Libro libro = libroRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Libro","")); 
        return mapearADto(libro);
    }

    @Override
    public LibroDto actualizarLibro(LibroDto libroDto, String id) {
        if(libroRepository.existsByTitulo(libroDto.getTitulo())){
            throw new ResourceBadRequest("El Titulo ingresado ya existe en la base de datos");
        }
        Libro libro = buscarLibro(id);
        Autor autor=buscarAutor(libro.getAutor().getId());
        Editorial editorial=buscarEditorial(libroDto.getEditorial().getId());        
        libro.setTitulo(libroDto.getTitulo());
        libro.setAnio(libroDto.getAnio());
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        Libro libroGuardado = libroRepository.save(libro);
        return mapearADto(libroGuardado);
    }

    @Override
    public void eliminarLibro(String id) {
        Libro libro = libroRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Libro", "para eliminar"));
		libroRepository.delete(libro);                                                               // Tools | Templates.
    }

    @Override
    public LibroDto mapearADto(Libro libro) {
        LibroDto libroDto = modelMapper.map(libro, LibroDto.class);
        return libroDto;
    }

    @Override
    public Libro mapearAEntidad(LibroDto libroDto) {
        Libro libro = modelMapper.map(libroDto, Libro.class);
        return libro;
    }

    @Override
    public List<LibroDto> obtenerLibrosPorAutorYEditorial(String autor, String editorial) {
        List<Libro> filtradoDeLibros = libroRepository.librosAutorYEditorial(autor,editorial);
        return filtradoDeLibros.stream().map(libro -> mapearADto(libro)).collect(Collectors.toList());
    }
    

    private Libro buscarLibro(String libroId) {
        return libroRepository.findById(libroId)
        .orElseThrow(() -> new ResourceNotFoundException("Libro","para actualizarlo"));
    }
    private Autor buscarAutor(String autorId) {
        return autorRepository.findById(autorId)
        .orElseThrow(() -> new ResourceNotFoundException("Autor","para guardar libro"));
    }
    private Editorial buscarEditorial(String editorialId) {
        return editorialRepository.findById(editorialId)
              .orElseThrow(() -> new ResourceNotFoundException("Editorial","para guardar libro")); 
    }

}
