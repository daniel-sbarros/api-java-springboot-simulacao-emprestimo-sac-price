package com.caixa.hackathon.api.produto;

public record DadosListagemProduto(Long id, String nome, Double taxaJuros, Integer minMeses, Integer maxMeses, Double minValor, Double maxValor) {
}
