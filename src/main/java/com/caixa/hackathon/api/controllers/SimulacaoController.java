package com.caixa.hackathon.api.controllers;

import com.caixa.hackathon.api.produto.Produto;
import com.caixa.hackathon.api.produto.ProdutoRepository;
import com.caixa.hackathon.api.simulacao.EntradaSimulacaoDTO;
import com.caixa.hackathon.api.simulacao.SimulacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("simulacao")
public class SimulacaoController {
    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    public ResponseEntity<?> listar(@RequestBody EntradaSimulacaoDTO requisicao) {
        if (requisicao.valorDesejado() < 200){
            return ResponseEntity.badRequest().body("{ \"error-message\":\"O Valor desejado deve ser maior ou igual a 200.\" }");
        }

        // RETORNA O PRODUTO ONDE O VALOR_DESEJADO, ENVIADO NA REQUISIÇÃO POST, ESTÁ ENTRE O VALOR MÁXIMO(VR_MAXIMO) E VALOR MÍNIMO(VR_MINIMO) DELE
        List<Produto> produtos = repository.findByMinValorLessThanEqualAndMaxValorGreaterThanEqualOrMaxValorIsNull(requisicao.valorDesejado(), requisicao.valorDesejado());

        // PEGA O PRIMEIRO PRODUTO ENCONTRADO
        Produto produto = produtos.stream().findFirst().get();

        // VERIFICAR SE O PRAZO É VÁLIDO E EXECUTAR SIMULAÇÃO
        if (produto.getMaxMeses() == null & (requisicao.prazo() >= produto.getMinMeses())) {
            return ResponseEntity.ok(retornaSimulacao(produto, requisicao));
        }
        else if (requisicao.prazo() >= produto.getMinMeses() && requisicao.prazo() <= produto.getMaxMeses()) {
            return ResponseEntity.ok(retornaSimulacao(produto, requisicao));
        } else {
            return ResponseEntity.badRequest().body(errorMessage(produto.getMinMeses(), produto.getMaxMeses()));
        }
    }

    private SimulacaoDTO retornaSimulacao(Produto produto, EntradaSimulacaoDTO requisicao){
        SimulacaoDTO simulacao = new SimulacaoDTO(produto, requisicao);
        return simulacao;
    }

    private String errorMessage(Object min, Object max){
        if (max != null){
            return "{ \"error-message\":\"Prazo inválido para o valor solicitado. Adicione um prazo com o valor entre " + min + " e " + max + ".\" }";
        }
        else{
            return "{ \"error-message\":\"Prazo inválido para o valor solicitado. Adicione um prazo com o valor maior que " + min + ".\" }";
        }
    }
}