package com.YEP4510.YEP4510.Services;

import com.YEP4510.YEP4510.Models.Responsavel;
import com.YEP4510.YEP4510.Repositories.ResponsavelRepository;
import com.YEP4510.YEP4510.RequestDTO.ResponsavelRequestDTO;
import com.YEP4510.YEP4510.ResponseDTO.ResponsavelResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;
    public ResponsavelService(ResponsavelRepository responsavelRepository){
        this.responsavelRepository = responsavelRepository;
    }

    public ResponsavelResponseDTO criarResponsavel(ResponsavelRequestDTO dto){
        Responsavel responsavel = new Responsavel(dto.getNome(), dto.getRg(), dto.getCpf(),
                dto.getEmail(), dto.getTelefone(), dto.getTipo());

        Responsavel salvo = responsavelRepository.save(responsavel);
        return new ResponsavelResponseDTO(responsavel.getId(), responsavel.getNome(),
                responsavel.getFichaInscricao().getId(), responsavel.getTipo());
    }

    public List<ResponsavelResponseDTO> obterTodos(){
        return StreamSupport.stream(responsavelRepository.findAll().spliterator(),false)
                .map(responsavel -> new ResponsavelResponseDTO(responsavel.getId(),
                        responsavel.getNome(), responsavel.getFichaInscricao().getId(), responsavel.getTipo()))
                .collect(Collectors.toList());
    }

    public ResponsavelResponseDTO obterPorId(long id){
        Responsavel responsavel = responsavelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));

        return new ResponsavelResponseDTO(responsavel.getId(), responsavel.getNome(), responsavel.getFichaInscricao().getId(), responsavel.getTipo());
    }

    public String editarResponsavel (long id, ResponsavelRequestDTO dto){
        Responsavel responsavel = responsavelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Responsavel não encontrado"));

        responsavel.setNome(dto.getNome());
        responsavel.setRg(dto.getRg());
        responsavel.setCpf(dto.getCpf());
        responsavel.setEmail(dto.getEmail());
        responsavel.setTelefone(dto.getTelefone());
        responsavel.setTipo(dto.getTipo());

        responsavelRepository.save(responsavel);
        return "Responsável alterado!";
    }
}
