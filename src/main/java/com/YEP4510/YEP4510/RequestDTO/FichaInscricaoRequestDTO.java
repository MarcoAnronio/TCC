package com.YEP4510.YEP4510.RequestDTO;

import com.YEP4510.YEP4510.Enum.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaInscricaoRequestDTO {
    private String nome;
    private LocalDate dataNasc;
    private String cep;
    private int serieEscolar;
    private String telefone;
    private String email;
    private Sexo sexo;

    private Long clubeId;

    private List<ResponsavelRequestDTO> responsaveis;
}
