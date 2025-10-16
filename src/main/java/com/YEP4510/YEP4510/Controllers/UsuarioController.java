package com.YEP4510.YEP4510.Controllers;

import com.YEP4510.YEP4510.RequestDTO.UsuarioRequestDTO;
import com.YEP4510.YEP4510.ResponseDTO.UsuarioResponseDTO;
import com.YEP4510.YEP4510.Services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService; //Criando atributo para armazenar o repositório

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@Valid @RequestBody UsuarioRequestDTO dto){
        UsuarioResponseDTO response = usuarioService.criarUsuario(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public List<UsuarioResponseDTO> obterTodosUsuarios(){
        return usuarioService.obterTodos();
    }

    @PutMapping("/{id}/senha")
    public ResponseEntity<String> editarUsuario(@PathVariable int id, @Valid @RequestBody UsuarioRequestDTO dto){
        String mensagem = usuarioService.editarUsuario(id, dto);
        return ResponseEntity.ok(mensagem);
    }


}