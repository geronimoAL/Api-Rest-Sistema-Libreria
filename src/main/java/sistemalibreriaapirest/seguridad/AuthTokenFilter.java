package sistemalibreriaapirest.seguridad;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import sistemalibreriaapirest.errors.BlogAppExcepcion;
import sistemalibreriaapirest.serviceImpl.CustomUserDetailsService;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // obtenemos el token de la solicitud HTTP
        String token = obtenerJWTdeLaSolicitud(request);

        // validamos el token
        try{
        if (StringUtils.hasText(token) && jwtUtils.validarToken(token)) {
            // obtenemos el username del token
            String username = jwtUtils.obtenerUsernameDelJWT(token);

            // cargamos el usuario asociado al token
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // establecemos la seguridad
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
       } catch (BlogAppExcepcion e) {
        logger.error("Error en el token del usuario: {}", e);
      }
        filterChain.doFilter(request, response);

    }

    // Bearer token de acceso
    private String obtenerJWTdeLaSolicitud(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

}
