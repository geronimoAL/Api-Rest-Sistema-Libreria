package sistemalibreriaapirest.service;

import java.util.List;

import sistemalibreriaapirest.domain.Editorial;
import sistemalibreriaapirest.dto.EditorialDto;

public interface EditorialServicio {

    public EditorialDto crearEditorial(EditorialDto libroDto);

    public List<EditorialDto> obtenerTodasLasEditoriales();

    public EditorialDto obtenerEditorialPorId(String id);

    public EditorialDto actualizarEditorial(EditorialDto libro, String id);

    public void eliminarEditorial(String id);
    
    public EditorialDto mapearADto(Editorial libro);
    
    public Editorial mapearAEntidad(EditorialDto libroDto);
    
}
