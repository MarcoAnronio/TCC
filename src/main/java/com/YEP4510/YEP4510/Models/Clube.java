package com.YEP4510.YEP4510.Models;

import com.YEP4510.YEP4510.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Clube {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nomeClube;
    private String cnpj;
    private String cep;
    private String cidade;
    private String endereco;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "clube")
    private Oficial oficial;

    @OneToOne(mappedBy = "clube")
    private  Presidente presidente;

    @OneToMany(mappedBy = "clube")
    private List<FichaInscricao> fichas;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.status = Status.ATIVO;
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    public Clube(String nomeClube, String cnpj, String cep, String cidade, String endereco, List<FichaInscricao> fichas) {
        this.nomeClube = nomeClube;
        this.cnpj = cnpj;
        this.cep = cep;
        this.cidade = cidade;
        this.endereco = endereco;
        this.fichas = fichas;
    }
}
