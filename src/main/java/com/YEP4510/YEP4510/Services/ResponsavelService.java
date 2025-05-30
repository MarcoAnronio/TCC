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
        Responsavel responsavel = new Responsavel(dto.getNome(), dto.getRg(), dto.getCpf()
                , dto.getEmail(), dto.getTelefone(), dto.getTipo(), dto.getFichaInscricao());

        Responsavel salvo = responsavelRepository.save(responsavel);
        return new ResponsavelResponseDTO(responsavel.getId(), responsavel.getNome(),
                responsavel.getFichaInscricao(), responsavel.getTipo());
    }

    public List<ResponsavelResponseDTO> obterTodos(){
        return StreamSupport.stream(responsavelRepository.findAll().spliterator(),false)
                .map(responsavel -> new ResponsavelResponseDTO(responsavel.getId(),
                        responsavel.getNome(), responsavel.getFichaInscricao(), responsavel.getTipo()))
                .collect(Collectors.toList());
    }
}

/*


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
    }*/
