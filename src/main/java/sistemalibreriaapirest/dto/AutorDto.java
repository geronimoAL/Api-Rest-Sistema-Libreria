package sistemalibreriaapirest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class AutorDto {

    private String id;
    
    @NotEmpty
    @Size(min = 2 ,message = "El titulo del libro deberia tener al menos 2 caracteres")
    private String Nombre;
}
