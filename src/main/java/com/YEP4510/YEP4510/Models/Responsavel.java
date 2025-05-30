package com.YEP4510.YEP4510.Models;

import com.YEP4510.YEP4510.Enum.TipoResponsavel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Responsavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String rg;
    private String cpf;
    private String email;
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoResponsavel tipo;

    @ManyToOne
    @JoinColumn(name = "id_ficha")
    private FichaInscricao fichaInscricao;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Responsavel(String nome, String rg, String cpf, String email, String telefone, TipoResponsavel tipo, FichaInscricao fichaInscricao) {
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.tipo = tipo;
        this.fichaInscricao = fichaInscricao;
    }
}
