package com.YEP4510.YEP4510.RequestDTO;

import com.YEP4510.YEP4510.Enum.TipoResponsavel;
import com.YEP4510.YEP4510.Models.FichaInscricao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsavelRequestDTO {
    private String nome;
    private String rg;
    private String cpf;
    private String email;
    private String telefone;
    private TipoResponsavel tipo;
    private FichaInscricao fichaInscricao;
}
