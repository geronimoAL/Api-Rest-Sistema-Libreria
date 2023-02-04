package sistemalibreriaapirest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceBadRequest extends RuntimeException{
    private String nombreDelCampo;

    public static final long serialVersionUID = 1L;

    public ResourceBadRequest( String nombreDelCampo) {
        super(nombreDelCampo);
        this.nombreDelCampo = nombreDelCampo;
    }

    public String getNombreDelCampo() {
        return nombreDelCampo;
    }

    public void setNombreDelCampo(String nombreDelCampo) {
        this.nombreDelCampo = nombreDelCampo;
    }
    

}
