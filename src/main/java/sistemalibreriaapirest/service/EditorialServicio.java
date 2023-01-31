package sistemalibreriaapirest.service;

import java.util.List;

import sistemalibreriaapirest.domain.Editorial;
import sistemalibreriaapirest.dto.EditorialDto;

public interface EditorialServicio {

    public EditorialDto crearPublicacion(EditorialDto libroDto);

    public List<EditorialDto> obtenerTodasLasEditoriales();

    public EditorialDto obtenerPublicacionPorId(String id);

    public EditorialDto actualizarPublicacion(EditorialDto libro, String id);

    public void eliminarPublicacion(String id);
    
    public EditorialDto mapearADto(Editorial libro);
    
    public Editorial mapearAEntidad(EditorialDto libroDto);
    
}
