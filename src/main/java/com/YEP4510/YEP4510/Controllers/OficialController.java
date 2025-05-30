package com.YEP4510.YEP4510.Controllers;

import com.YEP4510.YEP4510.RequestDTO.OficialRequestDTO;
import com.YEP4510.YEP4510.ResponseDTO.OficialResponseDTO;
import com.YEP4510.YEP4510.Services.OficialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oficial")
@RequiredArgsConstructor
public class OficialController {

    private final OficialService oficialService;

    @PostMapping
    public ResponseEntity<OficialResponseDTO> criarOficial(@Valid @RequestBody OficialRequestDTO dto){
        OficialResponseDTO response = oficialService.criarOficial(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public List<OficialResponseDTO> obterTodosOficiais(){
        return oficialService.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OficialResponseDTO> obterOficialPorId(@PathVariable int id){
        OficialResponseDTO response = oficialService.obterPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarOficial(@PathVariable int id, @Valid @RequestBody OficialRequestDTO dto){
        String mensagem = oficialService.editarOficial(id, dto);
        return ResponseEntity.ok(mensagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarOficial(@PathVariable int id){
        oficialService.deletarOficial(id);
        return ResponseEntity.ok("Oficial deletado com sucesso");
    }
}
