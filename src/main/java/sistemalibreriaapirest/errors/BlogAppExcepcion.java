package sistemalibreriaapirest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BlogAppExcepcion extends RuntimeException{
    private static final long serialVersionUID = 1L;

	private HttpStatus estado;
	private String mensaje;

	public BlogAppExcepcion(HttpStatus estado, String mensaje) {
		super(mensaje);
		this.estado = estado;
		this.mensaje = mensaje;
	}

	// public BlogAppExcepcion(HttpStatus estado, String mensaje, String mensaje1) {
	// 	super();
	// 	this.estado = estado;
	// 	this.mensaje = mensaje;
	// 	this.mensaje = mensaje1;
	// }

	public HttpStatus getEstado() {
		return estado;
	}

	public void setEstado(HttpStatus estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
