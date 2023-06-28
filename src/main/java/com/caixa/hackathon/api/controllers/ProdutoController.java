package com.caixa.hackathon.api.controllers;

import com.caixa.hackathon.api.produto.DadosListagemProduto;
import com.caixa.hackathon.api.produto.Produto;
import com.caixa.hackathon.api.produto.ProdutoRepository;
import com.caixa.hackathon.api.simulacao.SimulacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public List<Produto> listar() {
        return repository.findAll();
    }
}
