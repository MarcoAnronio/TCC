package com.YEP4510.YEP4510.Controllers;

import com.YEP4510.YEP4510.RequestDTO.ClubeRequestDTO;
import com.YEP4510.YEP4510.RequestDTO.UsuarioRequestDTO;
import com.YEP4510.YEP4510.ResponseDTO.UsuarioResponseDTO;
import com.YEP4510.YEP4510.ResponseDTO.ClubeResponseDTO;
import com.YEP4510.YEP4510.Services.ClubeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clube")
@RequiredArgsConstructor
public class ClubeController {

    private final ClubeService clubeService;

    @PostMapping
    public ResponseEntity<ClubeResponseDTO> criarClube(@Valid @RequestBody ClubeRequestDTO dto){
        ClubeResponseDTO response = clubeService.criarClube(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public List<ClubeResponseDTO> obterTodosClubes(){
        return clubeService.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubeResponseDTO> obterClubePorId(@PathVariable int id) {
        ClubeResponseDTO response = clubeService.obterPorId(id);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> editarClube(@PathVariable int id, @Valid @RequestBody ClubeRequestDTO dto){
        String mensagem = clubeService.editarClube(id, dto);
        return ResponseEntity.ok(mensagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarClube(@PathVariable int id) {
        clubeService.deletarClube(id);
        return ResponseEntity.ok("Clube deletado com sucesso!");
    }


}
