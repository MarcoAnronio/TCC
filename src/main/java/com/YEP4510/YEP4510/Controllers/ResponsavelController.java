package com.YEP4510.YEP4510.Controllers;

import com.YEP4510.YEP4510.RequestDTO.ResponsavelRequestDTO;
import com.YEP4510.YEP4510.ResponseDTO.ClubeResponseDTO;
import com.YEP4510.YEP4510.ResponseDTO.ResponsavelResponseDTO;
import com.YEP4510.YEP4510.Services.ResponsavelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsavel")
@RequiredArgsConstructor
public class ResponsavelController {

    private final ResponsavelService responsavelService;

    @PostMapping
    public ResponseEntity<ResponsavelResponseDTO> criarResponsavel(@Valid @RequestBody ResponsavelRequestDTO dto){
        ResponsavelResponseDTO response = responsavelService.criarResponsavel(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public List<ResponsavelResponseDTO> obterTodos(){
        return responsavelService.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelResponseDTO> obterPorId(@PathVariable int id){
        ResponsavelResponseDTO response = responsavelService.obterPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarResponsavel(@PathVariable int id, @Valid @RequestBody ResponsavelRequestDTO dto){
        String mensagem = responsavelService.editarResponsavel(id, dto);
        return ResponseEntity.ok(mensagem);
    }
}
