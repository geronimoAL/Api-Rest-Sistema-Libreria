package sistemalibreriaapirest.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistemalibreriaapirest.domain.Autor;
import sistemalibreriaapirest.dto.AutorDto;
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
    public AutorDto crearPublicacion(AutorDto autorDto) {
        Autor autor = mapearAEntidad(autorDto);
        Autor autorGuardado = autorRepository.save(autor);
        AutorDto autorRespuesta = mapearADto(autorGuardado);
        return autorRespuesta;
    }

    @Override
    public List<AutorDto> obtenerTodasLasPublicaciones() {
        List<Autor> autoresGuardados = autorRepository.findAll();
        return autoresGuardados.stream().map(autor -> mapearADto(autor)).collect(Collectors.toList());
    }

    @Override
    public AutorDto obtenerPublicacionPorId(String id) {
        Autor autor = autorRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id)); 
        return mapearADto(autor);
    }

    @Override
    public AutorDto actualizarPublicacion(AutorDto autorDto, String id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

        autor.setNombre(autorDto.getNombre());

        Autor autorGuardado = autorRepository.save(autor);
        return mapearADto(autorGuardado);
    }

    @Override
    public void eliminarPublicacion(String id) {
        Autor autor = autorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
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
