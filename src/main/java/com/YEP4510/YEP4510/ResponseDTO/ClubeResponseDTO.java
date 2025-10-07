package com.YEP4510.YEP4510.ResponseDTO;

import com.YEP4510.YEP4510.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubeResponseDTO {
    private long id;
    private String nomeClube;
    private String cnpj;
    private String cep;
    private String cidade;
    private String endereco;
    private Status status;

    private PresidenteResponse presidente;
    private OficialResponse oficial;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PresidenteResponse {
        private long id;
        private String nome;
        private String cpf;
        private String rg;
        private String email;
        private String telefone;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OficialResponse {
        private long id;
        private String nome;
        private String cpf;
        private String rg;
        private String email;
        private String telefone;
    }
}
