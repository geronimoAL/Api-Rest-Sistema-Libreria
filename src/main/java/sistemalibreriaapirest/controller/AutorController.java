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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import sistemalibreriaapirest.domain.Autor;
import sistemalibreriaapirest.dto.AutorDto;
import sistemalibreriaapirest.service.AutorServicio;

@RestController
@RequestMapping("/api/autor")
public class AutorController {
    
    @Autowired
    private AutorServicio autorServicio;
    
    @Operation(summary = "El rol ADMIN es el que puede guardar la editorial")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Created", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Autor.class)) }),
        @ApiResponse(responseCode = "404", description = "El nombre del autor ingresado ya existe en la BBDD", 
          content = @Content(mediaType = "application/json")) })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<AutorDto> guardarLibro(@Valid @RequestBody AutorDto autorDto) {
        return new ResponseEntity<>(autorServicio.crearAutor(autorDto), HttpStatus.CREATED);
    }

    @Operation(summary = "El rol ADMIN es el que puede guardar la editorial")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Autor.class)) }) })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista")
    public List<AutorDto> lista() {
           return  autorServicio.obtenerTodosLosAutores();
    }

    @Operation(summary = "El rol ADMIN es el que puede guardar la editorial")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Autor.class)) }),
        @ApiResponse(responseCode = "400", description = "El nombre del autor ingresado ya existe en la BBDD", 
          content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404", description = "El autor no se encontró para actualizarlo", 
          content = @Content(mediaType = "application/json"))})
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
	 public ResponseEntity<AutorDto> actualizarPublicacion(@Valid @RequestBody AutorDto autorDto,
			@PathVariable(name = "id") String id ) {
		AutorDto publicacionRespuesta = autorServicio.actualizarAutor(autorDto, id);
		return new ResponseEntity<>(publicacionRespuesta, HttpStatus.CREATED);
	}

    @Operation(summary = "El rol ADMIN es el que puede guardar la editorial")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Created", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Autor.class)) }),
        @ApiResponse(responseCode = "404", description = "El autor no se encontró para eliminarlo", 
          content = @Content(mediaType = "application/json")) })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") String id) {
		autorServicio.eliminarAutor(id);
		return new ResponseEntity<>("Autor eliminada con exito", HttpStatus.OK);
	}

    @Operation(summary = "El rol ADMIN es el que puede guardar la editorial")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Autor.class)) }),
        @ApiResponse(responseCode = "404", description = "El autor no se encontró en la bbdd", 
          content = @Content(mediaType = "application/json"))})
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/buscar/{id}")
	public ResponseEntity<AutorDto> obtenerPublicacionPorId(@PathVariable(name = "id") String id) {
		return ResponseEntity. ok(autorServicio. obtenerAutorPorId(id));
	}
}
