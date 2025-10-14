package com.YEP4510.YEP4510.Services;

import com.YEP4510.YEP4510.Models.Presidente;
import com.YEP4510.YEP4510.Repositories.PresidenteRepository;
import com.YEP4510.YEP4510.RequestDTO.PresidenteRequestDTO;
import com.YEP4510.YEP4510.ResponseDTO.PresidenteResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PresidenteService {

    private final PresidenteRepository presidenteRepository;
    public PresidenteService(PresidenteRepository presidenteRepository){
        this.presidenteRepository = presidenteRepository;
    }

    public PresidenteResponseDTO criarPresidente (PresidenteRequestDTO dto){
        Presidente presidente = new Presidente(dto.getNome(), dto.getCpf(),dto.getRg(), dto.getEmail(),dto.getTelefone());
        Presidente salvo = presidenteRepository.save(presidente);
        return new PresidenteResponseDTO(presidente.getId(), presidente.getNome());
    }

    public List<PresidenteResponseDTO> obterTodos(){
        return StreamSupport.stream(presidenteRepository.findAll().spliterator(),false)
                .map(presidente -> new PresidenteResponseDTO(presidente.getId(), presidente.getNome()))
                .collect(Collectors.toList());
    }

    public PresidenteResponseDTO obterPorId(long id){
        Presidente presidente = presidenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presidente não encontrado"));

        return new PresidenteResponseDTO(presidente.getId(), presidente.getNome());
    }

    public String editarPresidente (long id, PresidenteRequestDTO dto){
        Presidente presidente = presidenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presidente não encontrado"));

        presidente.setNome(dto.getNome());
        presidente.setCpf(dto.getCpf());
        presidente.setRg(dto.getRg());
        presidente.setEmail(dto.getEmail());
        presidente.setTelefone(dto.getTelefone());

        presidenteRepository.save(presidente);

        return "Presidente alterado";
    }

    public void deletarPresidente(long id){
        Presidente presidente = presidenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presidente não encontrado"));
        presidenteRepository.delete(presidente);
    }

}