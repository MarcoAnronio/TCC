package com.YEP4510.YEP4510.Controllers;

import com.YEP4510.YEP4510.Models.Presidente;
import com.YEP4510.YEP4510.RequestDTO.PresidenteRequestDTO;
import com.YEP4510.YEP4510.ResponseDTO.PresidenteResponseDTO;
import com.YEP4510.YEP4510.Services.PresidenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presidente")
@RequiredArgsConstructor
public class PresidenteController {

    private final PresidenteService presidenteService;

    @PostMapping
    public ResponseEntity<PresidenteResponseDTO> criarPresidente(@Valid @RequestBody PresidenteRequestDTO dto) {
        PresidenteResponseDTO response = presidenteService.criarPresidente(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public List<PresidenteResponseDTO> obterTodosPresidentes() {
        return presidenteService.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PresidenteResponseDTO> obterOficialPorId(@PathVariable int id) {
        PresidenteResponseDTO response = presidenteService.obterPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarPresidente(@PathVariable int id, @Valid @RequestBody PresidenteRequestDTO dto) {
        String mensagem = presidenteService.editarPresidente(id, dto);
        return ResponseEntity.ok(mensagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPresidente(@PathVariable int id) {
        presidenteService.deletarPresidente(id);
        return ResponseEntity.ok("Oficial deletado com sucesso");

    }
}