package sistemalibreriaapirest.errors;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.validation.FieldError;
import sistemalibreriaapirest.dto.ErrorDetalles;

@ControllerAdvice
public class GlobalExcepcionHandler  extends ResponseEntityExceptionHandler{
     

    @ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetalles> manejarResourceNotFoundException(ResourceNotFoundException exception,WebRequest webRequest){
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(),exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalles,HttpStatus.NOT_FOUND);
	}

    @ExceptionHandler(BlogAppExcepcion.class)
	public ResponseEntity<ErrorDetalles> manejarBlogAppException(BlogAppExcepcion exception,WebRequest webRequest){
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(),exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalles,HttpStatus.BAD_REQUEST);
	}
//si comento esto y el metodo de abajo cuando intento acceder a un recurso sin token me mostrara el mensaje personalizado de la clase AuthEntryPointJwt
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetalles> manejarGlobalException(Exception exception,WebRequest webRequest){
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(),exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalles,HttpStatus.INTERNAL_SERVER_ERROR);
	}
//si accedo a un recurso sin token podria funcionar el de arriba tambien si comento esto pero el codigo de estado será 500
	@ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetalles> manejarAccesoDenegado(Exception exception,WebRequest webRequest){
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(),exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalles,HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(ResourceBadRequest.class)
    public ResponseEntity<ErrorDetalles> manejarBadRequestException(ResourceBadRequest exception,WebRequest webRequest){
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(),exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalles,HttpStatus.BAD_REQUEST);
	}
	
	//voy a controlar los errores con este método.
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		    Map<String, String> errores = new HashMap<>();
          //te obtiene todos los errores
		    ex.getBindingResult().getAllErrors().forEach((error) -> {
		 //obtener el campo donde me está votando el error
			String nombreCampo = ((FieldError)error).getField();
		// el mensaje como el "titulo debe tener..."
			String mensaje = error.getDefaultMessage();
			
			errores.put(nombreCampo, mensaje);
		});
		
		return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);
	}

}
