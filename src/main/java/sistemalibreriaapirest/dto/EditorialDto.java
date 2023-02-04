package sistemalibreriaapirest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;




public class EditorialDto {
    
    private String id;
    
    @NotEmpty
    @Size(min = 2 ,message = "El titulo de la editorial deberia tener al menos 2 caracteres")
    private String Nombre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public EditorialDto() {
    }

    public EditorialDto(String id,
            @NotEmpty @Size(min = 2, message = "El titulo de la editorial deberia tener al menos 2 caracteres") String nombre) {
        this.id = id;
        Nombre = nombre;
    }

    
    
}
