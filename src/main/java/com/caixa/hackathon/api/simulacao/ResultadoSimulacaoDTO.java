package com.caixa.hackathon.api.simulacao;

import com.caixa.hackathon.api.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class ResultadoSimulacaoDTO {
    private String tipo;
    private List<ParcelaDTO> parcelas;

    public ResultadoSimulacaoDTO(String tipo){
        this.tipo = tipo;
    }
    public void setParcelas(Double valor, Integer prazo, Double taxa){
        if (this.getTipo().equals("SAC")) parcelas = Calculo.calculaSac(valor, prazo, taxa);
        else if (this.getTipo().equals("PRICE")) parcelas = Calculo.calculaPrice(valor, prazo, taxa);
        else parcelas = null;
    }

}

