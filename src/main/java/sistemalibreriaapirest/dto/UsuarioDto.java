package sistemalibreriaapirest.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UsuarioDto {
    @NotEmpty(message = "No puede ser vacio el nombre")
    private String nombre;
    @Email(message = "El correo ingresado es incorrecto.")
	private String email;
    @Size(min=6,message = "La contraseña debe tener al menos 6 digitos")
	private String password;
    private String repeatPassword;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRepeatPassword() {
        return repeatPassword;
    }
    public UsuarioDto() {
    }
    
}
