package com.YEP4510.YEP4510.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Classificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id_ficha", nullable = false)
    private FichaInscricao ficha;
    private double historicoEscolar;
    private double treinamento;
    private double reunioes;
    private double prova;
    private double total;

    @Column(name = "pais_escolhido", nullable = true)
    private String paisEscolhido;

}
