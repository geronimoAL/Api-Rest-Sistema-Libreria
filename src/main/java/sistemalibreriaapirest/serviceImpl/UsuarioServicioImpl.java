package sistemalibreriaapirest.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import sistemalibreriaapirest.domain.Rol;
import sistemalibreriaapirest.domain.Usuario;
import sistemalibreriaapirest.dto.UsuarioDto;
import sistemalibreriaapirest.errors.BlogAppExcepcion;
import sistemalibreriaapirest.errors.ResourceNotFoundException;
import sistemalibreriaapirest.repository.RolRepository;
import sistemalibreriaapirest.repository.UsuarioRepository;
import sistemalibreriaapirest.service.UsuarioServicio;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void registrar(UsuarioDto usuarioDto) {
      if(usuarioRepository.existsByEmail(usuarioDto.getEmail())){
        throw new BlogAppExcepcion(HttpStatus.BAD_REQUEST, "El email ingresado ya existe.");
      } 
    Usuario usuario=mapearAEntidad(usuarioDto);
    Rol rol=rolRepository.buscarPorNombre("USER");
    usuario.setEstado(true);
    usuario.addRole(rol);
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(usuarioDto.getPassword());
    usuario.setPassword(encodedPassword);
    usuarioRepository.save(usuario);
    }

    @Override
    public void modificar(UsuarioDto usuarioDto,String id) {
        Optional<Usuario>respuesta=usuarioRepository.findById(id);
        if(respuesta.isPresent()){
           Usuario usuarioModificado=mapearAEntidad(usuarioDto);
           usuarioRepository.save(usuarioModificado);
        }else{
            throw new ResourceNotFoundException("Usuario ", "id", id);
        }
    }

    @Override
    public List<UsuarioDto> listaUsuarios() {
        List<Usuario>lista=usuarioRepository.findAll();
        return lista.stream().map(usuario -> mapearADto(usuario)).collect(Collectors.toList());
    }

    @Override
    public void habilitar(UsuarioDto usuarioDto) {
        
    }

    @Override
    public void deshabilitar(UsuarioDto usuarioDto) {
        
    }

    @Override
    public UsuarioDto mapearADto(Usuario usuario) {
        UsuarioDto usuarioDto=modelMapper.map(usuario, UsuarioDto.class);
        return usuarioDto;
    }

    @Override
    public Usuario mapearAEntidad(UsuarioDto usuarioDto) {
        Usuario usuario=modelMapper.map(usuarioDto, Usuario.class);
        return usuario;
    }

}
