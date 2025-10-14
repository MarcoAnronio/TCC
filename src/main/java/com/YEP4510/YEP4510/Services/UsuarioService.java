package com.YEP4510.YEP4510.Services;

import com.YEP4510.YEP4510.Models.Usuario;
import com.YEP4510.YEP4510.Repositories.UsuarioRepository;
import com.YEP4510.YEP4510.RequestDTO.UsuarioRequestDTO;
import com.YEP4510.YEP4510.RequestDTO.UsuarioResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto){
        Usuario usuario = new Usuario(dto.getNome(), dto.getLogin(), dto.getSenha());
        Usuario salvo = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuario.getNome(), usuario.getLogin());
    }

    public List<UsuarioResponseDTO> obterTodos() {
        return StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
                .map(usuario -> new UsuarioResponseDTO(usuario.getNome(), usuario.getLogin()))
                .collect(Collectors.toList());
    }

    public String editarUsuario (long id, UsuarioRequestDTO dto){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        if (dto.getSenha() == null || dto.getSenha().isBlank()){
            return "Senha inválida";
        }

        usuario.setSenha(dto.getSenha());

        usuarioRepository.save(usuario);

        return "Senha alterada";
    }

}
