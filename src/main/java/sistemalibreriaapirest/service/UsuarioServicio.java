package sistemalibreriaapirest.service;

import java.util.List;

import sistemalibreriaapirest.domain.Usuario;
import sistemalibreriaapirest.dto.UsuarioDto;

public interface UsuarioServicio {
    public void registrar (UsuarioDto usuarioDto);
    public void modificar (UsuarioDto usuarioDto,String id);
    public List<UsuarioDto>listaUsuarios();
    public void habilitar(UsuarioDto usuarioDto);
    public void deshabilitar(UsuarioDto usuarioDto);
    public UsuarioDto mapearADto(Usuario usuario);
    public Usuario mapearAEntidad(UsuarioDto usuarioDto);
}
