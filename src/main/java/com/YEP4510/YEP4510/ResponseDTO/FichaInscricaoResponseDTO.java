package com.YEP4510.YEP4510.ResponseDTO;

import com.YEP4510.YEP4510.Enum.Sexo;
import com.YEP4510.YEP4510.Models.Clube;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaInscricaoResponseDTO {
    private Long id;
    private String nome;
    private LocalDate dataNasc;
    private String cep;
    private int serieEscolar;
    private String telefone;
    private String email;
    private Sexo sexo;

    private ClubeResponseDTO clubeId;

    private List<ResponsavelResponseDTO> responsaveis;
}


