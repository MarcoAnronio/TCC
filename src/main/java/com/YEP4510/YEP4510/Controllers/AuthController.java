package com.YEP4510.YEP4510.Controllers;

import com.YEP4510.YEP4510.Models.Usuario;
import com.YEP4510.YEP4510.Repositories.UsuarioRepository;
import com.YEP4510.YEP4510.RequestDTO.UsuarioResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000") // ou seu domínio em produção
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario credentials) {
        Usuario usuario = usuarioRepository.findByLogin(credentials.getLogin())
                .orElse(null);

        if (usuario == null || !usuario.getSenha().equals(credentials.getSenha())) {
            return ResponseEntity.status(401).body("Usuário ou senha incorretos!");
        }

        // ✅ Evita erro se tipo estiver nulo
        String tipo = (usuario.getTipo() != null)
                ? usuario.getTipo().name()
                : "CANDIDATO";

        UsuarioResponseDTO response = new UsuarioResponseDTO(
                usuario.getNome(),
                usuario.getLogin(),
                tipo
        );

        return ResponseEntity.ok(response);
    }

}
