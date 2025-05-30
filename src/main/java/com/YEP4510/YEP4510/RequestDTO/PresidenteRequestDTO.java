package com.YEP4510.YEP4510.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PresidenteRequestDTO {
    private String nome;
    private String cpf;
    private String rg;
    private String email;
    private String telefone;
}
