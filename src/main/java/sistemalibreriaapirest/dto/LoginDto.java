package sistemalibreriaapirest.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class LoginDto {
    @Email(message = "El correo ingresado es incorrecto.")
    String email;
    @Size(min=6,message = "La contraseña debe tener al menos 6 digitos")
    String password;
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

}
