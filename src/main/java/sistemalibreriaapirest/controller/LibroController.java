package sistemalibreriaapirest.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sistemalibreriaapirest.dto.LibroDto;
import sistemalibreriaapirest.service.LibroServicio;

@RestController
@RequestMapping("/api/libro")
public class LibroController {

    @Autowired
    private LibroServicio libroServicio;

    @PostMapping("/guardar")
    public ResponseEntity<LibroDto> guardarLibro(@Valid @RequestBody LibroDto libroDto) {
        return new ResponseEntity<>(libroServicio.crearPublicacion(libroDto), HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public List<LibroDto> lista() {
           return  libroServicio.obtenerTodasLasPublicaciones();
    }
    @PutMapping("/{id}")
	 public ResponseEntity<LibroDto> actualizarPublicacion(@Valid @RequestBody LibroDto libroDto,
			@PathVariable(name = "id") String id ) {
		LibroDto publicacionRespuesta = libroServicio.actualizarPublicacion(libroDto, id);
		return new ResponseEntity<>(publicacionRespuesta, HttpStatus.CREATED);
	}
    @DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") String id) {
		libroServicio.eliminarPublicacion(id);
		return new ResponseEntity<>("Publicacion eliminada con exito", HttpStatus.OK);
	}
    @GetMapping("/buscar/{id}")
	public ResponseEntity<LibroDto> obtenerPublicacionPorId(@PathVariable(name = "id") String id) {
		return ResponseEntity. ok(libroServicio. obtenerPublicacionPorId(id));
	}

}
