package sistemalibreriaapirest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistemalibreriaapirest.dto.EditorialDto;
import sistemalibreriaapirest.service.EditorialServicio;

@RestController
@RequestMapping("/api/editorial")
public class EditorialController {
    @Autowired
    private EditorialServicio editorialServicio;
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<EditorialDto> guardarLibro(@Valid @RequestBody EditorialDto editorialDto) {
        return new ResponseEntity<>(editorialServicio.crearPublicacion(editorialDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/lista")
    public List<EditorialDto> lista() {
           return  editorialServicio.obtenerTodasLasEditoriales();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
	 public ResponseEntity<EditorialDto> actualizarPublicacion(@Valid @RequestBody EditorialDto editorialDto,
			@PathVariable(name = "id") String id ) {
        EditorialDto publicacionRespuesta = editorialServicio.actualizarPublicacion(editorialDto, id);
		return new ResponseEntity<>(publicacionRespuesta, HttpStatus.CREATED);
	}

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") String id) {
		editorialServicio.eliminarPublicacion(id);
		return new ResponseEntity<>("Editorial eliminada con exito", HttpStatus.OK);
	}
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/buscar/{id}")
	public ResponseEntity<EditorialDto> obtenerPublicacionPorId(@PathVariable(name = "id") String id) {
		return ResponseEntity. ok(editorialServicio. obtenerPublicacionPorId(id));
	}
}
