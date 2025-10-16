package com.YEP4510.YEP4510.Controllers;

import com.YEP4510.YEP4510.Models.Classificacao;
import com.YEP4510.YEP4510.RequestDTO.ClassificacaoRequestDTO;
import com.YEP4510.YEP4510.RequestDTO.ClubeRequestDTO;
import com.YEP4510.YEP4510.ResponseDTO.ClassificacaoResponseDTO;
import com.YEP4510.YEP4510.ResponseDTO.ClubeResponseDTO;
import com.YEP4510.YEP4510.Services.ClassificacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classificacao")
@RequiredArgsConstructor
public class ClassificacaoController {

    private final ClassificacaoService classificacaoService;


    @PostMapping
    public ResponseEntity<ClassificacaoResponseDTO> salvarNotas(@Valid @RequestBody ClassificacaoRequestDTO dto){
        ClassificacaoResponseDTO response = classificacaoService.salvarNotas(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public List<ClassificacaoResponseDTO> listarTodos() {
        return classificacaoService.obterTodos();
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarClassificacao(
            @PathVariable long id,
            @RequestBody ClassificacaoRequestDTO dto) {

        String mensagem = classificacaoService.atualizarClassificacao(id, dto);
        return ResponseEntity.ok(mensagem);
    }



}
