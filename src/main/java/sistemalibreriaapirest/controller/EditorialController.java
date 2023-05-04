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
import sistemalibreriaapirest.domain.Editorial;
import sistemalibreriaapirest.dto.EditorialDto;
import sistemalibreriaapirest.service.EditorialServicio;

@RestController
@RequestMapping("/api/editorial")
public class EditorialController {
    @Autowired
    private EditorialServicio editorialServicio;
    

    @Operation(summary = "El rol ADMIN es el que puede guardar la editorial")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Editorial.class)) }),
        @ApiResponse(responseCode = "400", description = "El nombre del autor ingresado ya existe en la BBDD", 
          content = @Content(mediaType = "aplication/json"))})
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<EditorialDto> guardarEditorial(@Valid @RequestBody EditorialDto editorialDto) {
        return new ResponseEntity<>(editorialServicio.crearEditorial(editorialDto), HttpStatus.CREATED);
    }

    @Operation(summary = "El rol ADMIN es el que puede guardar la editorial")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Editorial.class)) })})
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista")
    public List<EditorialDto> lista() {
           return  editorialServicio.obtenerTodasLasEditoriales();
    }

    @Operation(summary = "El rol ADMIN es el que puede guardar la editorial")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Editorial.class)) }),
        @ApiResponse(responseCode = "400", description = "El nombre que ingresaste de la editorial ya existe en la BBDD", 
          content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404", description = "Editorial no encontrado para actualizarlo", 
          content = @Content(mediaType = "application/json"))})
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
	 public ResponseEntity<EditorialDto> actualizarEditorial(@Valid @RequestBody EditorialDto editorialDto,
			@PathVariable(name = "id") String id ) {
        EditorialDto publicacionRespuesta = editorialServicio.actualizarEditorial(editorialDto, id);
		return new ResponseEntity<>(publicacionRespuesta, HttpStatus.CREATED);
	}
 
    @Operation(summary = "El rol ADMIN es el que puede guardar la editorial")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Editorial.class)) }),
        @ApiResponse(responseCode = "400", description = "El nombre que ingresaste de la editorial ya existe en la BBDD", 
          content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404", description = "Editorial no encontrado para eliminarlo", 
          content = @Content(mediaType = "application/json"))})
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<String> eliminarEditorial(@PathVariable(name = "id") String id) {
		editorialServicio.eliminarEditorial(id);
		return new ResponseEntity<>("Editorial eliminada con exito", HttpStatus.OK);
	}


    @Operation(summary = "El rol ADMIN es el que puede guardar la editorial")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Ok", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Editorial.class)) }),
          @ApiResponse(responseCode = "404", description = "Editorial no encontrado", 
          content = @Content(mediaType = "application/json"))})
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/buscar/{id}")
	public ResponseEntity<EditorialDto> obtenerEditorialPorId(@PathVariable(name = "id") String id) {
		return ResponseEntity. ok(editorialServicio. obtenerEditorialPorId(id));
	}
}
