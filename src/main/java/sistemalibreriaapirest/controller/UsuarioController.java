package sistemalibreriaapirest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sistemalibreriaapirest.dto.JwtAuthResponseDto;
import sistemalibreriaapirest.dto.LoginDto;
import sistemalibreriaapirest.dto.UsuarioDto;
import sistemalibreriaapirest.errors.ResourceBadRequest;
import sistemalibreriaapirest.seguridad.JwtUtils;
import sistemalibreriaapirest.serviceImpl.UsuarioServicioImpl;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServicioImpl usuarioServicioImpl;

    @Autowired
	private DaoAuthenticationProvider authenticationProvider;

    @Autowired
	private JwtUtils jwtUtils;
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@Valid @RequestBody UsuarioDto usuarioDto){
        if(!usuarioDto.getPassword().equals(usuarioDto.getRepeatPassword())){
          throw new ResourceBadRequest("Las contraseñas no coinciden");
        }

        usuarioServicioImpl.registrar(usuarioDto);;
        
        return new ResponseEntity<>("¡Gracias por registrarte en nuestra aplicación! Para acceder a nuestros recursos, por favor inicia sesión con tu dirección de correo electrónico y contraseña en la página de inicio de sesión. Si tienes algún problema para acceder a tu cuenta, no dudes en ponerte en contacto con nuestro equipo de soporte técnico.",HttpStatus.OK);   
    }

    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody LoginDto loginDto){
		Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
        String token = jwtUtils.generarToken(authentication);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        char letra='@';
        int posicion=authentication.getName().indexOf(letra);
        String nombre=authentication.getName().substring(0, posicion);
        String message = String.format("Bienvenido %s !!", nombre);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);

	}

}
