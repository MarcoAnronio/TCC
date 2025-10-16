package com.YEP4510.YEP4510.Services;

import com.YEP4510.YEP4510.Models.Classificacao;
import com.YEP4510.YEP4510.Models.FichaInscricao;
import com.YEP4510.YEP4510.Models.Responsavel;
import com.YEP4510.YEP4510.Repositories.ClassificacaoRepository;
import com.YEP4510.YEP4510.Repositories.FichaInscricaoRepository;
import com.YEP4510.YEP4510.RequestDTO.ClassificacaoRequestDTO;
import com.YEP4510.YEP4510.RequestDTO.ResponsavelRequestDTO;
import com.YEP4510.YEP4510.ResponseDTO.ClassificacaoResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClassificacaoService {

    private final ClassificacaoRepository classificacaoRepository;
    private final FichaInscricaoRepository fichaRepository;

    public ClassificacaoService(ClassificacaoRepository classificacaoRepository, FichaInscricaoRepository fichaRepository) {
        this.classificacaoRepository = classificacaoRepository;
        this.fichaRepository = fichaRepository;
    }

    public ClassificacaoResponseDTO salvarNotas(ClassificacaoRequestDTO dto) {

        FichaInscricao ficha = fichaRepository.findById(dto.getFichaId())
                .orElseThrow(() -> new RuntimeException("Ficha não encontrada"));

        double total = dto.getHistoricoEscolar() + dto.getTreinamento() + dto.getReunioes() + dto.getProva();

        Classificacao classificacao = new Classificacao();
        classificacao.setFicha(ficha);
        classificacao.setHistoricoEscolar(dto.getHistoricoEscolar());
        classificacao.setTreinamento(dto.getTreinamento());
        classificacao.setReunioes(dto.getReunioes());
        classificacao.setProva(dto.getProva());
        classificacao.setTotal(total);

        Classificacao salvo = classificacaoRepository.save(classificacao);

        return new ClassificacaoResponseDTO(
                salvo.getFicha().getId(),
                salvo.getFicha().getNome(),
                salvo.getFicha().getClube().getNomeClube(),
                salvo.getHistoricoEscolar(),
                salvo.getTreinamento(),
                salvo.getReunioes(),
                salvo.getProva(),
                salvo.getTotal(),
                salvo.getPaisEscolhido()
        );
    }


    public List<ClassificacaoResponseDTO> obterTodos() {
        return StreamSupport.stream(classificacaoRepository.findAll().spliterator(), false)
                .sorted((a, b) -> Double.compare(b.getTotal(), a.getTotal()))
                .map(classificacao -> {
                    var ficha = classificacao.getFicha();
                    String nomeClube = ficha.getClube() != null ? ficha.getClube().getNomeClube() : "—";
                    String cidade = ficha.getCidade() != null ? ficha.getCidade() : "—";

                    return new ClassificacaoResponseDTO(
                            ficha.getId(),
                            ficha.getNome(),
                            nomeClube,
                            classificacao.getHistoricoEscolar(),
                            classificacao.getTreinamento(),
                            classificacao.getReunioes(),
                            classificacao.getProva(),
                            classificacao.getTotal(),
                            classificacao.getPaisEscolhido()
                    );
                })
                .collect(Collectors.toList());
    }



    public String atualizarClassificacao(long fichaId, ClassificacaoRequestDTO dto) {
        Classificacao classificacao = classificacaoRepository.findByFichaId(fichaId)
                .orElseThrow(() -> new RuntimeException("Classificação não encontrada para essa ficha."));

        FichaInscricao ficha = classificacao.getFicha();
        classificacao.setFicha(ficha);

        classificacao.setHistoricoEscolar(dto.getHistoricoEscolar());
        classificacao.setTreinamento(dto.getTreinamento());
        classificacao.setReunioes(dto.getReunioes());
        classificacao.setProva(dto.getProva());

        classificacao.setTotal(
                dto.getHistoricoEscolar() +
                        dto.getTreinamento() +
                        dto.getReunioes() +
                        dto.getProva()
        );

        if (dto.getPaisEscolhido() != null && !dto.getPaisEscolhido().isBlank()) {
            classificacao.setPaisEscolhido(dto.getPaisEscolhido());
        }

        classificacaoRepository.save(classificacao);
        return "Classificação atualizada com sucesso!";
    }




}
