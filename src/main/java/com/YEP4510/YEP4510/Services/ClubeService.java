package com.YEP4510.YEP4510.Services;

import com.YEP4510.YEP4510.Enum.Status;
import com.YEP4510.YEP4510.Models.Clube;
import com.YEP4510.YEP4510.Models.Oficial;
import com.YEP4510.YEP4510.Models.Presidente;
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

    public ClubeResponseDTO criarClube(ClubeRequestDTO dto) {
        Presidente presidente = new Presidente(
                dto.getPresidente().getNome(),
                dto.getPresidente().getCpf(),
                dto.getPresidente().getRg(),
                dto.getPresidente().getEmail(),
                dto.getPresidente().getTelefone()
        );

        Oficial oficial = new Oficial(
                dto.getOficial().getNome(),
                dto.getOficial().getCpf(),
                dto.getOficial().getRg(),
                dto.getOficial().getEmail(),
                dto.getOficial().getTelefone()
        );

        Clube clube = new Clube();
        clube.setNomeClube(dto.getNomeClube());
        clube.setCnpj(dto.getCnpj());
        clube.setCep(dto.getCep());
        clube.setCidade(dto.getCidade());
        clube.setEndereco(dto.getEndereco());
        clube.setPresidente(presidente);
        clube.setOficial(oficial);

        // Define status padrão como ATIVO
        clube.setStatus(Status.ATIVO);

        Clube salvo = clubeRepository.save(clube);

        return new ClubeResponseDTO(
                salvo.getId(),
                salvo.getNomeClube(),
                salvo.getCnpj(),
                salvo.getCep(),
                salvo.getCidade(),
                salvo.getEndereco(),
                salvo.getStatus(),
                new ClubeResponseDTO.PresidenteResponse(
                        salvo.getPresidente().getId(),
                        salvo.getPresidente().getNome(),
                        salvo.getPresidente().getCpf(),
                        salvo.getPresidente().getRg(),
                        salvo.getPresidente().getEmail(),
                        salvo.getPresidente().getTelefone()
                ),
                new ClubeResponseDTO.OficialResponse(
                        salvo.getOficial().getId(),
                        salvo.getOficial().getNome(),
                        salvo.getOficial().getCpf(),
                        salvo.getOficial().getRg(),
                        salvo.getOficial().getEmail(),
                        salvo.getOficial().getTelefone()
                )
        );
    }


    public List<ClubeResponseDTO> obterTodos() {
        return StreamSupport.stream(clubeRepository.findAll().spliterator(), false)
                .map(clube -> new ClubeResponseDTO(
                        clube.getId(),
                        clube.getNomeClube(),
                        clube.getCnpj(),
                        clube.getCep(),
                        clube.getCidade(),
                        clube.getEndereco(),
                        clube.getStatus(),
                        new ClubeResponseDTO.PresidenteResponse(
                                clube.getPresidente().getId(),
                                clube.getPresidente().getNome(),
                                clube.getPresidente().getCpf(),
                                clube.getPresidente().getRg(),
                                clube.getPresidente().getEmail(),
                                clube.getPresidente().getTelefone()
                        ),
                        new ClubeResponseDTO.OficialResponse(
                                clube.getOficial().getId(),
                                clube.getOficial().getNome(),
                                clube.getOficial().getCpf(),
                                clube.getOficial().getRg(),
                                clube.getOficial().getEmail(),
                                clube.getOficial().getTelefone()
                        )
                ))
                .collect(Collectors.toList());
    }


    public ClubeResponseDTO obterPorId(long id) {
        Clube clube = clubeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clube não encontrado!"));

        return new ClubeResponseDTO(
                clube.getId(),
                clube.getNomeClube(),
                clube.getCnpj(),
                clube.getCep(),
                clube.getCidade(),
                clube.getEndereco(),
                clube.getStatus(),
                new ClubeResponseDTO.PresidenteResponse(
                        clube.getPresidente().getId(),
                        clube.getPresidente().getNome(),
                        clube.getPresidente().getCpf(),
                        clube.getPresidente().getRg(),
                        clube.getPresidente().getEmail(),
                        clube.getPresidente().getTelefone()
                ),
                new ClubeResponseDTO.OficialResponse(
                        clube.getOficial().getId(),
                        clube.getOficial().getNome(),
                        clube.getOficial().getCpf(),
                        clube.getOficial().getRg(),
                        clube.getOficial().getEmail(),
                        clube.getOficial().getTelefone()
                )
        );
    }


    public String editarClube (long id, ClubeRequestDTO dto){
        Clube clube = clubeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clube não encontado!"));

        clube.setNomeClube(dto.getNomeClube());
        clube.setCnpj(dto.getCnpj());
        clube.setCep(dto.getCep());
        clube.setCidade(dto.getCidade());
        clube.setEndereco(dto.getEndereco());

        if (dto.getStatus() != null) {
            clube.setStatus(dto.getStatus());
        }

        clubeRepository.save(clube);

        return "Clube alterado!";
    }

    public void deletarClube(long id) {
        Clube clube = clubeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clube não encontrado!"));

        clubeRepository.delete(clube);
    }


}
