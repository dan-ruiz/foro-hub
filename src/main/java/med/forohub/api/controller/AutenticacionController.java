package med.forohub.api.controller;

import jakarta.validation.Valid;
import med.forohub.api.domain.entity.usuario.DatosAutenticacionUsuario;
import med.forohub.api.domain.entity.usuario.Usuario;
import med.forohub.api.domain.repository.UsuarioRepository;
import med.forohub.api.infra.security.DatosJWTToken;
import med.forohub.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping()
    public ResponseEntity<?> registrarYAutenticarUsuario(@RequestBody DatosAutenticacionUsuario datos) {
        // Crear el usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setLogin(datos.login());
        nuevoUsuario.setClave(passwordEncoder.encode(datos.clave()));
        usuarioRepository.save(nuevoUsuario);

        // Generar el JWT para el usuario recién creado
        String jwtToken = tokenService.generarToken(nuevoUsuario);

        return ResponseEntity.ok(new DatosJWTToken(jwtToken));
    }

}
