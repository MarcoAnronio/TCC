package com.YEP4510.YEP4510.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassificacaoRequestDTO {
    private long fichaId;
    private double historicoEscolar;
    private double treinamento;
    private double reunioes;
    private double prova;
    private double total;
    private String paisEscolhido;

}
