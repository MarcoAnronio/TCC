package com.YEP4510.YEP4510.ResponseDTO;

import com.YEP4510.YEP4510.Enum.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaInscricaoResponseDTO {
    private long id;
    private String nome;
    private LocalDate dataNasc;
    private String cep;
    private int serieEscolar;
    private String telefone;
    private String email;
    private Sexo sexo;
    private String cidade;
    private String endereco;
    private String pais1;
    private String pais2;
    private String pais3;
    private String pais4;

    private Long clubeId;

    private List<ResponsavelResponseDTO> responsaveis;
}


