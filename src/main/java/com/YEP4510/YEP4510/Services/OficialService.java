package com.YEP4510.YEP4510.Services;

import com.YEP4510.YEP4510.Models.Oficial;
import com.YEP4510.YEP4510.Repositories.OficialRepository;
import com.YEP4510.YEP4510.RequestDTO.OficialRequestDTO;
import com.YEP4510.YEP4510.ResponseDTO.ClubeResponseDTO;
import com.YEP4510.YEP4510.ResponseDTO.OficialResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OficialService {

    private final OficialRepository oficialRepository;
    public OficialService(OficialRepository oficialRepository){
        this.oficialRepository = oficialRepository;
    }

    public OficialResponseDTO criarOficial(OficialRequestDTO dto){
        Oficial oficial = new Oficial(dto.getNome(), dto.getCpf(),dto.getRg(), dto.getEmail(),dto.getTelefone());
        Oficial salvo = oficialRepository.save(oficial);
        return new OficialResponseDTO(oficial.getId(), oficial.getNome());
    }

    public List<OficialResponseDTO> obterTodos(){
        return StreamSupport.stream(oficialRepository.findAll().spliterator(),false)
                .map(oficial -> new OficialResponseDTO(oficial.getId(), oficial.getNome()))
                .collect(Collectors.toList());
    }

    public OficialResponseDTO obterPorId(long id){
        Oficial oficial = oficialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oficial não encontrado"));

        return new OficialResponseDTO(oficial.getId(), oficial.getNome());
    }

    public String editarOficial (long id, OficialRequestDTO dto){
        Oficial oficial = oficialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oficial não encontrado"));

        oficial.setNome(dto.getNome());
        oficial.setCpf(dto.getCpf());
        oficial.setRg(dto.getRg());
        oficial.setEmail(dto.getEmail());
        oficial.setTelefone(dto.getTelefone());

        oficialRepository.save(oficial);

        return "Oficial alterado";
    }

    public void deletarOficial(long id){
        Oficial oficial = oficialRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Oficial não encomntrado"));

        oficialRepository.delete(oficial);
    }

}