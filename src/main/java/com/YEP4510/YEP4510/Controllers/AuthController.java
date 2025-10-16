package com.YEP4510.YEP4510.Controllers;

import com.YEP4510.YEP4510.Models.Usuario;
import com.YEP4510.YEP4510.Repositories.UsuarioRepository;
import com.YEP4510.YEP4510.ResponseDTO.UsuarioResponseDTO;
import com.YEP4510.YEP4510.Security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario credentials) {
        Usuario usuario = usuarioRepository.findByLogin(credentials.getLogin())
                .orElse(null);

        if (usuario == null || !usuario.getSenha().equals(credentials.getSenha())) {
            return ResponseEntity.status(401).body("Usuário ou senha incorretos!");
        }

        String tipo = (usuario.getTipo() != null)
                ? usuario.getTipo().name()
                : "CANDIDATO";

        String token = jwtUtil.generateToken(usuario.getLogin(), tipo);

        UsuarioResponseDTO response = new UsuarioResponseDTO(
                usuario.getNome(),
                usuario.getLogin(),
                tipo
        );

        return ResponseEntity.ok(Map.of(
                "token", token,
                "usuario", response
        ));
    }
}
