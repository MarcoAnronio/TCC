package com.YEP4510.YEP4510.Models;

import com.YEP4510.YEP4510.Enum.Sexo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FichaInscricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private LocalDate dataNasc;
    private String cep;
    private int serieEscolar;
    private String telefone;
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;

    @ManyToOne
    @JoinColumn(name = "id_clube", nullable = false)
    private Clube clube;

    @OneToMany(mappedBy = "fichaInscricao", cascade = CascadeType.ALL)
    private List<Responsavel> responsaveis;
}
