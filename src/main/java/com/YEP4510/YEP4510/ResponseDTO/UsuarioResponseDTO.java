package com.YEP4510.YEP4510.ResponseDTO;

import com.YEP4510.YEP4510.Enum.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private String nome;
    private String login;
    private String tipo;
}
