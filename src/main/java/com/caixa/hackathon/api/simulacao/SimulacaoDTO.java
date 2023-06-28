package com.caixa.hackathon.api.simulacao;


import com.caixa.hackathon.api.produto.Produto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SimulacaoDTO {
    private Long codigoProduto;
    private String descricaoProduto;
    private Double taxaJuros;
    private List<ResultadoSimulacaoDTO> resultadoSimulacao;

    public SimulacaoDTO(Produto produto, EntradaSimulacaoDTO requisicao) {
        this.codigoProduto = produto.getId();
        this.descricaoProduto = produto.getNome();
        this.taxaJuros = produto.getTaxaJuros();

        ResultadoSimulacaoDTO sac = new ResultadoSimulacaoDTO("SAC");
        ResultadoSimulacaoDTO price = new ResultadoSimulacaoDTO("PRICE");
        sac.setParcelas(requisicao.valorDesejado(), requisicao.prazo(), produto.getTaxaJuros());
        price.setParcelas(requisicao.valorDesejado(), requisicao.prazo(), produto.getTaxaJuros());

        this.resultadoSimulacao = new ArrayList<>();
        this.resultadoSimulacao.add(sac);
        this.resultadoSimulacao.add(price);
    }
}
