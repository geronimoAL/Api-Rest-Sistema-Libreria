package sistemalibreriaapirest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;




public class AutorDto {

    private String id;
    
    @NotEmpty
    @Size(min = 2 ,message = "El titulo del libro deberia tener al menos 2 caracteres")
    private String Nombre;


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AutorDto(String id,
            @NotEmpty @Size(min = 2, message = "El titulo del libro deberia tener al menos 2 caracteres") String nombre) {
        this.id = id;
        Nombre = nombre;
    }

    public AutorDto() {
    }
    
    


}
