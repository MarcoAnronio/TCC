package com.YEP4510.YEP4510.ResponseDTO;

import com.YEP4510.YEP4510.Enum.TipoResponsavel;
import com.YEP4510.YEP4510.Models.FichaInscricao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsavelResponseDTO {
    private long id;
    private String nome;
    private long fichaInscricaoId;
    private TipoResponsavel tipo;
}
