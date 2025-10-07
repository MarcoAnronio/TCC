package com.YEP4510.YEP4510.Services;

import com.YEP4510.YEP4510.Models.*;
import com.YEP4510.YEP4510.Repositories.ClubeRepository;
import com.YEP4510.YEP4510.Repositories.FichaInscricaoRepository;
import com.YEP4510.YEP4510.Repositories.ResponsavelRepository;
import com.YEP4510.YEP4510.RequestDTO.FichaInscricaoRequestDTO;
import com.YEP4510.YEP4510.ResponseDTO.ClubeResponseDTO;
import com.YEP4510.YEP4510.ResponseDTO.FichaInscricaoResponseDTO;
import com.YEP4510.YEP4510.ResponseDTO.ResponsavelResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FichaInscricaoService {

    private final FichaInscricaoRepository fichaInscricaoRepository;
    private final ResponsavelRepository responsavelRepository;
    private final ClubeRepository clubeRepository;
    public FichaInscricaoService(FichaInscricaoRepository fichaInscricaoRepository, ResponsavelRepository responsavelRepository, ClubeRepository clubeRepository) {
        this.fichaInscricaoRepository = fichaInscricaoRepository;
        this.responsavelRepository = responsavelRepository;
        this.clubeRepository = clubeRepository;
    }

    public FichaInscricaoResponseDTO criarFicha(FichaInscricaoRequestDTO dto){

        Clube clube = clubeRepository.findById(dto.getClubeId())
                .orElseThrow(() -> new RuntimeException("Clube não encontrado"));

        FichaInscricao fichaInscricao = new FichaInscricao();
        fichaInscricao.setNome(dto.getNome());
        fichaInscricao.setDataNasc(dto.getDataNasc());
        fichaInscricao.setCep(dto.getCep());
        fichaInscricao.setSerieEscolar(dto.getSerieEscolar());
        fichaInscricao.setTelefone(dto.getTelefone());
        fichaInscricao.setEmail(dto.getEmail());
        fichaInscricao.setSexo(dto.getSexo());
        fichaInscricao.setClube(clube);

        List<Responsavel> responsaveis = dto.getResponsaveis().stream()
                .map(rdto -> {
                    Responsavel responsavel = new Responsavel();
                    responsavel.setNome(rdto.getNome());
                    responsavel.setRg(rdto.getRg());
                    responsavel.setCpf(rdto.getCpf());
                    responsavel.setEmail(rdto.getEmail());
                    responsavel.setTelefone(rdto.getTelefone());
                    responsavel.setTipo(rdto.getTipo());
                    responsavel.setFichaInscricao(fichaInscricao); // vincula à ficha
                    return responsavel;
                })
                .collect(Collectors.toList());

        fichaInscricao.setResponsaveis(responsaveis);

        FichaInscricao fichaSalva = fichaInscricaoRepository.save(fichaInscricao);

        ClubeResponseDTO clubeDTO = new ClubeResponseDTO(
                fichaSalva.getClube().getId(),
                fichaSalva.getClube().getNomeClube(),
                fichaSalva.getClube().getCnpj(),
                fichaSalva.getClube().getCep(),
                fichaSalva.getClube().getCidade(),
                fichaSalva.getClube().getEndereco(),
                fichaSalva.getClube().getStatus(),
                new ClubeResponseDTO.PresidenteResponse(
                        fichaSalva.getClube().getPresidente().getId(),
                        fichaSalva.getClube().getPresidente().getNome(),
                        fichaSalva.getClube().getPresidente().getCpf(),
                        fichaSalva.getClube().getPresidente().getRg(),
                        fichaSalva.getClube().getPresidente().getEmail(),
                        fichaSalva.getClube().getPresidente().getTelefone()
                ),
                new ClubeResponseDTO.OficialResponse(
                        fichaSalva.getClube().getOficial().getId(),
                        fichaSalva.getClube().getOficial().getNome(),
                        fichaSalva.getClube().getOficial().getCpf(),
                        fichaSalva.getClube().getOficial().getRg(),
                        fichaSalva.getClube().getOficial().getEmail(),
                        fichaSalva.getClube().getOficial().getTelefone()
                )
        );


        List<ResponsavelResponseDTO> responsavelDTOs = fichaSalva.getResponsaveis().stream()
                .map(r -> new ResponsavelResponseDTO(
                        r.getId(),
                        r.getNome(),
                        r.getFichaInscricao().getId(),
                        r.getTipo()
                ))
                .collect(Collectors.toList());

        return new FichaInscricaoResponseDTO(
                fichaSalva.getId(),
                fichaSalva.getNome(),
                fichaSalva.getDataNasc(),
                fichaSalva.getCep(),
                fichaSalva.getSerieEscolar(),
                fichaSalva.getTelefone(),
                fichaSalva.getEmail(),
                fichaSalva.getSexo(),
                clubeDTO,
                responsavelDTOs
        );
    }

    public List<FichaInscricaoResponseDTO> obterTodos() {
        return StreamSupport.stream(fichaInscricaoRepository.findAll().spliterator(), false)
                .map(fichaInscricao -> {
                    // Transforma o Clube da entidade em ClubeResponseDTO
                    ClubeResponseDTO clubeDTO = new ClubeResponseDTO(
                            fichaInscricao.getClube().getId(),
                            fichaInscricao.getClube().getNomeClube(),
                            fichaInscricao.getClube().getCnpj(),
                            fichaInscricao.getClube().getCep(),
                            fichaInscricao.getClube().getCidade(),
                            fichaInscricao.getClube().getEndereco(),
                            fichaInscricao.getClube().getStatus(),
                            new ClubeResponseDTO.PresidenteResponse(
                                    fichaInscricao.getClube().getPresidente().getId(),
                                    fichaInscricao.getClube().getPresidente().getNome(),
                                    fichaInscricao.getClube().getPresidente().getCpf(),
                                    fichaInscricao.getClube().getPresidente().getRg(),
                                    fichaInscricao.getClube().getPresidente().getEmail(),
                                    fichaInscricao.getClube().getPresidente().getTelefone()
                            ),
                            new ClubeResponseDTO.OficialResponse(
                                    fichaInscricao.getClube().getOficial().getId(),
                                    fichaInscricao.getClube().getOficial().getNome(),
                                    fichaInscricao.getClube().getOficial().getCpf(),
                                    fichaInscricao.getClube().getOficial().getRg(),
                                    fichaInscricao.getClube().getOficial().getEmail(),
                                    fichaInscricao.getClube().getOficial().getTelefone()
                            )
                    );


                    // Transforma a lista de Responsavel em lista de ResponsavelResponseDTO
                    List<ResponsavelResponseDTO> responsavelDTOs = fichaInscricao.getResponsaveis().stream()
                            .map(r -> new ResponsavelResponseDTO(
                                    r.getId(),
                                    r.getNome(),
                                    r.getFichaInscricao().getId(),
                                    r.getTipo()
                            ))
                            .collect(Collectors.toList());

                    // Agora usa as variáveis clubeDTO e responsavelDTOs na criação do DTO final
                    return new FichaInscricaoResponseDTO(
                            fichaInscricao.getId(),
                            fichaInscricao.getNome(),
                            fichaInscricao.getDataNasc(),
                            fichaInscricao.getCep(),
                            fichaInscricao.getSerieEscolar(),
                            fichaInscricao.getTelefone(),
                            fichaInscricao.getEmail(),
                            fichaInscricao.getSexo(),
                            clubeDTO,
                            responsavelDTOs
                    );
                })
                .collect(Collectors.toList());
    }

    public FichaInscricaoResponseDTO obterPorId(int id) {
        FichaInscricao fichaInscricao = fichaInscricaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ficha de inscrição não encontrada"));

        ClubeResponseDTO clubeDTO = new ClubeResponseDTO(
                fichaInscricao.getClube().getId(),
                fichaInscricao.getClube().getNomeClube(),
                fichaInscricao.getClube().getCnpj(),
                fichaInscricao.getClube().getCep(),
                fichaInscricao.getClube().getCidade(),
                fichaInscricao.getClube().getEndereco(),
                fichaInscricao.getClube().getStatus(),
                new ClubeResponseDTO.PresidenteResponse(
                        fichaInscricao.getClube().getPresidente().getId(),
                        fichaInscricao.getClube().getPresidente().getNome(),
                        fichaInscricao.getClube().getPresidente().getCpf(),
                        fichaInscricao.getClube().getPresidente().getRg(),
                        fichaInscricao.getClube().getPresidente().getEmail(),
                        fichaInscricao.getClube().getPresidente().getTelefone()
                ),
                new ClubeResponseDTO.OficialResponse(
                        fichaInscricao.getClube().getOficial().getId(),
                        fichaInscricao.getClube().getOficial().getNome(),
                        fichaInscricao.getClube().getOficial().getCpf(),
                        fichaInscricao.getClube().getOficial().getRg(),
                        fichaInscricao.getClube().getOficial().getEmail(),
                        fichaInscricao.getClube().getOficial().getTelefone()
                )
        );

        List<ResponsavelResponseDTO> responsavelDTOs = fichaInscricao.getResponsaveis().stream()
                .map(r -> new ResponsavelResponseDTO(
                        r.getId(),
                        r.getNome(),
                        r.getFichaInscricao().getId(),
                        r.getTipo()
                ))
                .collect(Collectors.toList());

        return new FichaInscricaoResponseDTO(
                fichaInscricao.getId(),
                fichaInscricao.getNome(),
                fichaInscricao.getDataNasc(),
                fichaInscricao.getCep(),
                fichaInscricao.getSerieEscolar(),
                fichaInscricao.getTelefone(),
                fichaInscricao.getEmail(),
                fichaInscricao.getSexo(),
                clubeDTO,
                responsavelDTOs
        );
    }

    public FichaInscricaoResponseDTO atualizarFicha(int id, FichaInscricaoRequestDTO dto) {
        FichaInscricao fichaExistente = fichaInscricaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ficha de inscrição não encontrada"));

        Clube clube = clubeRepository.findById(dto.getClubeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clube não encontrado"));

        // Atualiza os campos
        fichaExistente.setNome(dto.getNome());
        fichaExistente.setDataNasc(dto.getDataNasc());
        fichaExistente.setCep(dto.getCep());
        fichaExistente.setSerieEscolar(dto.getSerieEscolar());
        fichaExistente.setTelefone(dto.getTelefone());
        fichaExistente.setEmail(dto.getEmail());
        fichaExistente.setSexo(dto.getSexo());
        fichaExistente.setClube(clube);

        // Remove os responsáveis antigos (CascadeType.ALL cuida disso)
        fichaExistente.getResponsaveis().clear();

        // Adiciona os novos responsáveis
        List<Responsavel> novosResponsaveis = dto.getResponsaveis().stream()
                .map(rdto -> {
                    Responsavel responsavel = new Responsavel();
                    responsavel.setNome(rdto.getNome());
                    responsavel.setRg(rdto.getRg());
                    responsavel.setCpf(rdto.getCpf());
                    responsavel.setEmail(rdto.getEmail());
                    responsavel.setTelefone(rdto.getTelefone());
                    responsavel.setTipo(rdto.getTipo());
                    responsavel.setFichaInscricao(fichaExistente);
                    return responsavel;
                })
                .collect(Collectors.toList());

        fichaExistente.setResponsaveis(novosResponsaveis);

        FichaInscricao fichaAtualizada = fichaInscricaoRepository.save(fichaExistente);

        // Constrói DTOs de resposta
        ClubeResponseDTO clubeDTO = new ClubeResponseDTO(
                fichaAtualizada.getClube().getId(),
                fichaAtualizada.getClube().getNomeClube(),
                fichaAtualizada.getClube().getCnpj(),
                fichaAtualizada.getClube().getCep(),
                fichaAtualizada.getClube().getCidade(),
                fichaAtualizada.getClube().getEndereco(),
                fichaAtualizada.getClube().getStatus(),
                new ClubeResponseDTO.PresidenteResponse(
                        fichaAtualizada.getClube().getPresidente().getId(),
                        fichaAtualizada.getClube().getPresidente().getNome(),
                        fichaAtualizada.getClube().getPresidente().getCpf(),
                        fichaAtualizada.getClube().getPresidente().getRg(),
                        fichaAtualizada.getClube().getPresidente().getEmail(),
                        fichaAtualizada.getClube().getPresidente().getTelefone()
                ),
                new ClubeResponseDTO.OficialResponse(
                        fichaAtualizada.getClube().getOficial().getId(),
                        fichaAtualizada.getClube().getOficial().getNome(),
                        fichaAtualizada.getClube().getOficial().getCpf(),
                        fichaAtualizada.getClube().getOficial().getRg(),
                        fichaAtualizada.getClube().getOficial().getEmail(),
                        fichaAtualizada.getClube().getOficial().getTelefone()
                )
        );

        List<ResponsavelResponseDTO> responsavelDTOs = fichaAtualizada.getResponsaveis().stream()
                .map(r -> new ResponsavelResponseDTO(
                        r.getId(),
                        r.getNome(),
                        r.getFichaInscricao().getId(),
                        r.getTipo()
                ))
                .collect(Collectors.toList());

        return new FichaInscricaoResponseDTO(
                fichaAtualizada.getId(),
                fichaAtualizada.getNome(),
                fichaAtualizada.getDataNasc(),
                fichaAtualizada.getCep(),
                fichaAtualizada.getSerieEscolar(),
                fichaAtualizada.getTelefone(),
                fichaAtualizada.getEmail(),
                fichaAtualizada.getSexo(),
                clubeDTO,
                responsavelDTOs
        );
    }

    public void deletarFicha(int id) {
        FichaInscricao ficha = fichaInscricaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ficha de inscrição não encontrada"));

        fichaInscricaoRepository.delete(ficha);
    }

}
