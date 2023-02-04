package sistemalibreriaapirest.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import sistemalibreriaapirest.domain.Editorial;
import sistemalibreriaapirest.dto.EditorialDto;
import sistemalibreriaapirest.errors.BlogAppExcepcion;
import sistemalibreriaapirest.errors.ResourceBadRequest;
import sistemalibreriaapirest.errors.ResourceNotFoundException;
import sistemalibreriaapirest.repository.EditorialRepository;
import sistemalibreriaapirest.service.EditorialServicio;

@Service
public class EditorialServicioImpl implements EditorialServicio {

    @Autowired
    private EditorialRepository editorialRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EditorialDto crearPublicacion(EditorialDto editorialDto) {
        Editorial editorial = mapearAEntidad(editorialDto);
        boolean editorialExiste=editorialRepository.existsByNombre(editorial.getNombre());
        if (editorialExiste){
            throw new ResourceBadRequest("El nombre de la editorial ya existe");
        }
        Editorial editorialGuardado = editorialRepository.save(editorial);
        EditorialDto editorialRespuesta = mapearADto(editorialGuardado);
        return editorialRespuesta;
    }

    @Override
    public List<EditorialDto> obtenerTodasLasEditoriales() {
        List<Editorial> editorialesGuardados = editorialRepository.findAll();
        return editorialesGuardados.stream().map(editorial -> mapearADto(editorial)).collect(Collectors.toList());
    }

    @Override
    public EditorialDto obtenerPublicacionPorId(String id) {
        Editorial editorial = editorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        return mapearADto(editorial);
    }

    @Override
    public EditorialDto actualizarPublicacion(EditorialDto editorialDto, String id) {
        Editorial editorial = editorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

        editorial.setNombre(editorialDto.getNombre());

        Editorial libroGuardado = editorialRepository.save(editorial);
        return mapearADto(libroGuardado);
    }

    @Override
    public void eliminarPublicacion(String id) {
        Editorial editorial = editorialRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
		editorialRepository.delete(editorial);
    }

    @Override
    public EditorialDto mapearADto(Editorial editorial) {
        EditorialDto libroDto = modelMapper.map(editorial, EditorialDto.class);
        return libroDto;
    }

    @Override
    public Editorial mapearAEntidad(EditorialDto editorialDto) {
        Editorial editorial = modelMapper.map(editorialDto, Editorial.class);
        return editorial;
    }

}
