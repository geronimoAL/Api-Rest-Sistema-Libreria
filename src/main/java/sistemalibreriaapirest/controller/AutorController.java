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

import sistemalibreriaapirest.dto.AutorDto;
import sistemalibreriaapirest.service.AutorServicio;

@RestController
@RequestMapping("/api/autor")
public class AutorController {
    
    @Autowired
    private AutorServicio autorServicio;
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<AutorDto> guardarLibro(@Valid @RequestBody AutorDto autorDto) {
        return new ResponseEntity<>(autorServicio.crearPublicacion(autorDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/lista")
    public List<AutorDto> lista() {
           return  autorServicio.obtenerTodasLasPublicaciones();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
	 public ResponseEntity<AutorDto> actualizarPublicacion(@Valid @RequestBody AutorDto autorDto,
			@PathVariable(name = "id") String id ) {
		AutorDto publicacionRespuesta = autorServicio.actualizarPublicacion(autorDto, id);
		return new ResponseEntity<>(publicacionRespuesta, HttpStatus.CREATED);
	}

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") String id) {
		autorServicio.eliminarPublicacion(id);
		return new ResponseEntity<>("Autor eliminada con exito", HttpStatus.OK);
	}

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/buscar/{id}")
	public ResponseEntity<AutorDto> obtenerPublicacionPorId(@PathVariable(name = "id") String id) {
		return ResponseEntity. ok(autorServicio. obtenerPublicacionPorId(id));
	}
}
