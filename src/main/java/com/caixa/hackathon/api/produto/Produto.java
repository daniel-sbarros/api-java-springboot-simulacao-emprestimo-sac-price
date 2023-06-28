package com.caixa.hackathon.api.produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CO_PRODUTO")
    private Long id;
    @Column(name = "NO_PRODUTO")
    private String nome;
    @Column(name = "PC_TAXA_JUROS")
    private Double taxaJuros;
    @Column(name = "NU_MINIMO_MESES")
    private Integer minMeses;
    @Column(name = "NU_MAXIMO_MESES")
    private Integer maxMeses;
    @Column(name = "VR_MINIMO")
    private Double minValor;
    @Column(name = "VR_MAXIMO")
    private Double maxValor;
}
