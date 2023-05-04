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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import sistemalibreriaapirest.domain.Libro;
import sistemalibreriaapirest.dto.LibroDto;
import sistemalibreriaapirest.service.LibroServicio;

@RestController
@RequestMapping("/api/libro")
public class LibroController {

    @Autowired
    private LibroServicio libroServicio;

    @Operation(summary = "El libro sólo lo puede guardar el rol ADMIN")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Created", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Libro.class)) }),
        @ApiResponse(responseCode = "400", description = "El Titulo ingresado ya existe en la base de datos", 
          content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404", description = "Autor/Editorial no encontrado para guardar libro", 
            content = @Content(mediaType = "application/json")) })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<LibroDto> guardarLibro(@Valid @RequestBody LibroDto libroDto) {
        return new ResponseEntity<>(libroServicio.crearLibro(libroDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Tanto el rol ADMIN como el rol User pueden solicitar este recurso")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Libro.class)) })})
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/lista")
    public List<LibroDto> lista() {
           return  libroServicio.obtenerTodosLosLibros();
    }

    @Operation(summary = "Tanto el rol ADMIN como el rol User pueden solicitar este recurso")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Libro.class)) }),
        @ApiResponse(responseCode = "404", description = "Autor/Editorial no encontrado para buscar por libro", 
          content = @Content(mediaType = "application/json")) })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/autor/{autor}/editorial/{editorial}")
    public List<LibroDto> filtradoLibros(@PathVariable(value = "autor")String autor,@PathVariable(value = "editorial")String editorial) {
           return  libroServicio.obtenerLibrosPorAutorYEditorial(autor, editorial);
    }

    @Operation(summary = "El libro sólo lo puede editar el rol ADMIN")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Libro.class)) }),
        @ApiResponse(responseCode = "404", description = "Autor/Editorial/Libro no encontrado para actualizarlo", 
          content = @Content(mediaType = "application/json")) })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
	 public ResponseEntity<LibroDto> actualizarPublicacion(@Valid @RequestBody LibroDto libroDto,
			@PathVariable(name = "id") String id ) {
		LibroDto publicacionRespuesta = libroServicio.actualizarLibro(libroDto, id);
		return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
	}
    
    @Operation(summary = "El libro sólo lo puede eliminar el rol ADMIN")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Libro eliminado con éxito", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Libro.class)) }),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado para eliminar", 
          content = @Content(mediaType = "application/json")) })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") String id) {
		libroServicio.eliminarLibro(id);
		return new ResponseEntity<>("Libro eliminado con exito", HttpStatus.OK);
	}

    @Operation(summary = "Tanto el rol ADMIN como el rol User pueden solicitar este recurso")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Libro.class)) }),
        @ApiResponse(responseCode = "404", description = "Libro no encontrado", 
          content = @Content(mediaType = "application/json")) })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/buscar/{id}")
	public ResponseEntity<LibroDto> obtenerPublicacionPorId(@PathVariable(name = "id") String id) {
		return ResponseEntity. ok(libroServicio. obtenerLibroPorId(id));
	}

}
