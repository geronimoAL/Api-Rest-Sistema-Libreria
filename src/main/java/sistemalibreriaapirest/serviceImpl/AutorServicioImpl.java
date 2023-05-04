package sistemalibreriaapirest.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistemalibreriaapirest.domain.Autor;
import sistemalibreriaapirest.dto.AutorDto;
import sistemalibreriaapirest.errors.ResourceBadRequest;
import sistemalibreriaapirest.errors.ResourceNotFoundException;
import sistemalibreriaapirest.repository.AutorRepository;
import sistemalibreriaapirest.service.AutorServicio;

@Service
public class AutorServicioImpl implements AutorServicio {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AutorDto crearAutor(AutorDto autorDto) {
        boolean autorExiste=autorRepository.existsByNombre(autorDto.getNombre());
        if (autorExiste){
            throw new ResourceBadRequest("El nombre del autor ingresado ya existe en la BBDD");
        }
        Autor autor = mapearAEntidad(autorDto);
        Autor autorGuardado = autorRepository.save(autor);
        AutorDto autorRespuesta = mapearADto(autorGuardado);
        return autorRespuesta;
    }

    @Override
    public List<AutorDto> obtenerTodosLosAutores() {
        List<Autor> autoresGuardados = autorRepository.findAll();
        return autoresGuardados.stream().map(autor -> mapearADto(autor)).collect(Collectors.toList());
    }

    @Override
    public AutorDto obtenerAutorPorId(String id) {
        Autor autor = autorRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("El autor", "en la bbdd")); 
        return mapearADto(autor);
    }

    @Override
    public AutorDto actualizarAutor(AutorDto autorDto, String id) {
        boolean autorExiste=autorRepository.existsByNombre(autorDto.getNombre());
        if (autorExiste){
            throw new ResourceBadRequest("El nombre del autor ingresado ya existe en la BBDD");
        }
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El autor", "para actualizarlo"));
        autor.setNombre(autorDto.getNombre());
        Autor autorGuardado = autorRepository.save(autor);
        return mapearADto(autorGuardado);
    }

    @Override
    public void eliminarAutor(String id) {
        Autor autor = autorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El autor", "para eliminarlo"));
		autorRepository.delete(autor); 
    }

    @Override
    public AutorDto mapearADto(Autor libro) {
        AutorDto autorDto = modelMapper.map(libro, AutorDto.class);
        return autorDto;
    }

    @Override
    public Autor mapearAEntidad(AutorDto libroDto) {
        Autor autor = modelMapper.map(libroDto, Autor.class);
        return autor;
    }
    
}
