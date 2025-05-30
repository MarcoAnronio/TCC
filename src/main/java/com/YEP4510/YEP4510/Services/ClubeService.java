package com.YEP4510.YEP4510.Services;

import com.YEP4510.YEP4510.Models.Clube;
import com.YEP4510.YEP4510.Repositories.ClubeRepository;
import com.YEP4510.YEP4510.RequestDTO.ClubeRequestDTO;
import com.YEP4510.YEP4510.ResponseDTO.ClubeResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class ClubeService {

    private final ClubeRepository clubeRepository;
    public ClubeService(ClubeRepository clubeRepository){
        this.clubeRepository = clubeRepository;
    }

    public ClubeResponseDTO criarClube(ClubeRequestDTO dto){
        Clube clube = new Clube(dto.getNomeClube(), dto.getCnpj(), dto.getCep(), dto.getCidade(), dto.getEndereco(), dto.getFichas());
        Clube salvo = clubeRepository.save(clube);
        return new ClubeResponseDTO(clube.getNomeClube(), clube.getCidade());
    }

    public List<ClubeResponseDTO> obterTodos(){
        return StreamSupport.stream(clubeRepository.findAll().spliterator(), false)
                .map(clube -> new ClubeResponseDTO(clube.getNomeClube(), clube.getCidade()))
                .collect(Collectors.toList());
    }

    public ClubeResponseDTO obterPorId(int id) {
        Clube clube = clubeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clube não encontrado!"));

        return new ClubeResponseDTO(clube.getNomeClube(), clube.getCidade());
    }


    public String editarClube (int id, ClubeRequestDTO dto){
        Clube clube = clubeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clube não encontado!"));

        clube.setNomeClube(dto.getNomeClube());
        clube.setCnpj(dto.getCnpj());
        clube.setCep(dto.getCep());
        clube.setCidade(dto.getCidade());
        clube.setEndereco(dto.getEndereco());
        clube.setFichas(dto.getFichas());

        clubeRepository.save(clube);

        return "Clube alterado!";
    }

    public void deletarClube(int id) {
        Clube clube = clubeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clube não encontrado!"));

        clubeRepository.delete(clube);
    }


}
