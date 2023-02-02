package sistemalibreriaapirest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import sistemalibreriaapirest.dto.JwtAuthResponseDto;
import sistemalibreriaapirest.dto.LoginDto;
import sistemalibreriaapirest.dto.UsuarioDto;
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
        usuarioServicioImpl.registrar(usuarioDto);;
        
        return new ResponseEntity<>("Usuario registrado exitosamente",HttpStatus.OK);   
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDto> autenticarUsuario(@RequestBody LoginDto loginDto){
		Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
        String token = jwtUtils.generarToken(authentication);

		return ResponseEntity.ok(new JwtAuthResponseDto(token));
	}

}
