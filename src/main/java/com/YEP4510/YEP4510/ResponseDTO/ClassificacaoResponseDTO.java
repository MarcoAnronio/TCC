package com.YEP4510.YEP4510.ResponseDTO;

import com.YEP4510.YEP4510.Models.Clube;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassificacaoResponseDTO {
    private long fichaId;
    private String nome;
    private String clube;
    private double historicoEscolar;
    private double treinamento;
    private double reunioes;
    private double prova;
    private double total;
}
