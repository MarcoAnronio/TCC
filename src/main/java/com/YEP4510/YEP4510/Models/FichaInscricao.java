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
    private String cidade;
    private String endereco;

    private String pais1;
    private String pais2;
    private String pais3;
    private String pais4;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;

    @ManyToOne
    @JoinColumn(name = "id_clube", nullable = false)
    private Clube clube;

    @OneToMany(mappedBy = "fichaInscricao", cascade = CascadeType.ALL)
    private List<Responsavel> responsaveis;


    public FichaInscricao(String nome, LocalDate dataNasc, String cep, int serieEscolar, String telefone, String email, String cidade, String endereco, String pais1, String pais2, String pais3, String pais4, Sexo sexo, Clube clube, List<Responsavel> responsaveis) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cep = cep;
        this.serieEscolar = serieEscolar;
        this.telefone = telefone;
        this.email = email;
        this.cidade = cidade;
        this.endereco = endereco;
        this.pais1 = pais1;
        this.pais2 = pais2;
        this.pais3 = pais3;
        this.pais4 = pais4;
        this.sexo = sexo;
        this.clube = clube;
        this.responsaveis = responsaveis;
    }
}
