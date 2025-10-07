package com.YEP4510.YEP4510.RequestDTO;

import com.YEP4510.YEP4510.Enum.Status;
import com.YEP4510.YEP4510.Models.FichaInscricao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubeRequestDTO {
    private String nomeClube;
    private String cnpj;
    private String cep;
    private String cidade;
    private String endereco;
    private Status status;

    private PresidenteRequestDTO presidente;
    private OficialRequestDTO oficial;
}
