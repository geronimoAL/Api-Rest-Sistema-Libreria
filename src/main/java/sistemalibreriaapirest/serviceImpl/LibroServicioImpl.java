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
    public LibroDto crearPublicacion(LibroDto libroDto) {
        Libro libro = mapearAEntidad(libroDto);
        Autor autor=autorRepository.findById(libroDto.getAutor().getId())
              .orElseThrow(() -> new ResourceNotFoundException("Autor id ", "id", libroDto.getAutor().getId()));        ;
        Editorial editorial=editorialRepository.findById(libroDto.getEditorial().getId())
              .orElseThrow(() -> new ResourceNotFoundException("Editorial id", "id", libroDto.getEditorial().getId())); 
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        Libro libroGuardado = libroRepository.save(libro);
        LibroDto libroRespuesta = mapearADto(libroGuardado);
        return libroRespuesta;
    }

    @Override
    public List<LibroDto> obtenerTodasLasPublicaciones() {
        List<Libro> librosGuardados = libroRepository.findAll();
        return librosGuardados.stream().map(libro -> mapearADto(libro)).collect(Collectors.toList());
    }


    @Override
    public LibroDto obtenerPublicacionPorId(String id) {
        Libro libro = libroRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id)); 
        return mapearADto(libro);
    }

    @Override
    public LibroDto actualizarPublicacion(LibroDto libroDto, String id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

        libro.setTitulo(libroDto.getTitulo());
        libro.setAnio(libroDto.getAnio());

        Libro libroGuardado = libroRepository.save(libro);
        return mapearADto(libroGuardado);
    }

    @Override
    public void eliminarPublicacion(String id) {
        Libro libro = libroRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
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
    public List<LibroDto> obtenerPublicacionesPorAutorYEditorial(String autor, String editorial) {
        List<Libro> filtradoDeLibros = libroRepository.librosAutorYEditorial(autor,editorial);
        return filtradoDeLibros.stream().map(libro -> mapearADto(libro)).collect(Collectors.toList());
    }
    

}
