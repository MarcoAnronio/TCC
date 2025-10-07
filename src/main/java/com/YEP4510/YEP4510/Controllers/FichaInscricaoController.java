package com.YEP4510.YEP4510.Controllers;

import com.YEP4510.YEP4510.Models.FichaInscricao;
import com.YEP4510.YEP4510.RequestDTO.ClubeRequestDTO;
import com.YEP4510.YEP4510.RequestDTO.FichaInscricaoRequestDTO;
import com.YEP4510.YEP4510.ResponseDTO.FichaInscricaoResponseDTO;
import com.YEP4510.YEP4510.Services.FichaInscricaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricao")
@RequiredArgsConstructor
public class FichaInscricaoController {

    private final FichaInscricaoService fichaInscricaoService;

    @PostMapping
    public ResponseEntity<FichaInscricaoResponseDTO> criarFicha(@Valid @RequestBody FichaInscricaoRequestDTO dto){
        FichaInscricaoResponseDTO response = fichaInscricaoService.criarFicha(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public List<FichaInscricaoResponseDTO> obterTodos(){
        return fichaInscricaoService.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FichaInscricaoResponseDTO> ObterPorId(@PathVariable int id){
        FichaInscricaoResponseDTO mensagem = fichaInscricaoService.obterPorId(id);
        return ResponseEntity.ok(mensagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FichaInscricaoResponseDTO> atualizarFicha(
            @PathVariable int id,
            @Valid @RequestBody FichaInscricaoRequestDTO dto) {

        FichaInscricaoResponseDTO fichaAtualizada = fichaInscricaoService.atualizarFicha(id, dto);
        return ResponseEntity.ok(fichaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarFicha(@PathVariable int id){
        fichaInscricaoService.deletarFicha(id);
        return ResponseEntity.ok("Ficha xcluída com sucesso");
    }
}
